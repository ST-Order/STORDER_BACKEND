package com.storder.order.order.repository;

import com.storder.order.order.dto.user.OrderSummaryDto;
import com.storder.order.order.entity.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            "SELECT COALESCE(SUM(o.finalPayment), 0) FROM Order o "
                    + "WHERE o.user.userId = :userId "
                    + "AND o.payedAt BETWEEN :startDate AND :endDate")
    Integer findTotalOrderAmountByUserAndPeriod(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query(
            "SELECT COUNT(o) FROM Order o "
                    + "WHERE o.user.userId = :userId "
                    + "AND o.payedAt BETWEEN :startDate AND :endDate")
    Integer findTotalOrderCountByUserAndPeriod(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);


    @Query("SELECT new com.storder.order.order.dto.user.OrderSummaryDto(" +
        "o.orderId, " +
        "o.createdAt, " +
        "s.storeImage, " +
        "s.storeName, " +
        "SUM(om.menuTotalPrice), " + // 가게별 총 주문 금액
        "o.endAt) " +  // 주문 완료 일시
        "FROM Order o " +
        "JOIN OrderMenu om ON om.order = o " +
        "JOIN om.menu m " +
        "JOIN m.store s " +
        "WHERE o.user.userId = :userId " +
        "AND o.orderStatus = 'READY' " + // 주문 상태가 READY인 것만 필터링
        "AND o.createdAt BETWEEN :startDate AND :endDate " +
        "GROUP BY o.orderId, s.storeId, o.createdAt, s.storeImage, s.storeName, o.endAt " + // 주문 ID 기준 그룹화
        "ORDER BY o.createdAt DESC")
    List<OrderSummaryDto> findUserOrdersWithinPeriod(
        @Param("userId") Long userId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate);
}
