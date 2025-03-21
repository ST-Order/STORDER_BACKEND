package com.storder.order.order.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class OrderSummaryDto {
    private Long orderId;
    private LocalDate orderDate;
    private String storeImage;
    private String storeName;
    private Long totalOrderPrice;
    private LocalDateTime endAt;

    public OrderSummaryDto(
            Long orderId,
            LocalDateTime createdAt,
            String storeImage,
            String storeName,
            Long totalOrderPrice,
            LocalDateTime endAt) {
        this.orderId = orderId;
        this.orderDate = createdAt.toLocalDate();
        this.storeImage = storeImage;
        this.storeName = storeName;
        this.totalOrderPrice = totalOrderPrice != null ? totalOrderPrice : 0L;
        // Long 타입 변환 및 null 방지
        this.endAt = endAt;
    }
}
