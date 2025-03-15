package com.storder.order.order.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class OrderSummaryDto {
    private Long orderId; // 주문 ID
    private LocalDate orderDate; // 주문 날짜
    private String storeImage; // 가게 이미지
    private String storeName; // 가게 이름
    private Long totalOrderPrice; // 가게별 총 주문 금액
    private LocalDateTime endAt; // 주문 완료 일시

    public OrderSummaryDto(
            Long orderId,
            LocalDateTime createdAt,
            String storeImage,
            String storeName,
            Long totalOrderPrice,
            LocalDateTime endAt) {
        this.orderId = orderId;
        this.orderDate = createdAt.toLocalDate(); // LocalDate로 변환
        this.storeImage = storeImage;
        this.storeName = storeName;
        this.totalOrderPrice =
                totalOrderPrice != null ? totalOrderPrice : 0L; // Long 타입 변환 및 null 방지
        this.endAt = endAt;
    }
}
