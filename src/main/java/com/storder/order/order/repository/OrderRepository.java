package com.storder.order.order.repository;

import com.storder.order.order.entity.Order;
import java.time.LocalDateTime;
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
}
