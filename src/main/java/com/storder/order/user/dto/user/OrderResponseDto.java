package com.storder.order.user.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {

    @Schema(description = "해당 기간 동안의 총 주문 금액")
    private int totalPrice;

    @Schema(description = "해당 기간 동안의 총 주문 횟수")
    private int totalOrderCount;

    @Schema(description = "주문 리스트")
    private List<OrderGroupDto> orders;

    @Getter
    @Builder
    public static class OrderGroupDto {
        @Schema(description = "주문 ID", example = "12345")
        private Long orderId;

        @Schema(description = "주문 날짜", example = "2025-03-20")
        private LocalDate orderDate;

        @Schema(description = "가게별 주문 리스트")
        private List<StoreOrderDto> storeOrders;
    }

    @Getter
    @Builder
    public static class StoreOrderDto {
        @Schema(description = "가게 이미지", example = "https://example.com/images/food1.jpg")
        private String storeImage;

        @Schema(description = "가게 이름", example = "경성카츠")
        private String storeName;

        @Schema(description = "가게별 총 주문 금액", example = "18000")
        private long totalOrderPrice;

        @Schema(description = "주문 완료 일시", example = "2025-03-20T17:00:00")
        private LocalDateTime endAt;
    }
}
