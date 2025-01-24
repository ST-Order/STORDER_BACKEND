package com.storder.order.order.dto.user;

import com.storder.order.order.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "주문 생성 응답 DTO")
@Builder
public class OrderCreateResponse {

    @Schema(description = "주문 ID", example = "1")
    private Long orderId;

    @Schema(description = "사용자 이름", example = "홍길동")
    private String userName;

    @Schema(description = "총 가격", example = "45000")
    private Integer totalPrice;

    @Schema(description = "주문 상태", example = "UNPAY")
    private OrderStatus orderStatus;

    @Schema(description = "주문 생성 시간", example = "2025-01-24T15:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "주문 목록")
    private List<OrderResponse> orders;

    @Data
    @Schema(description = "상점별 주문 DTO")
    public static class OrderResponse {

        @Schema(description = "상점 ID", example = "1")
        private Long storeId;

        @Schema(description = "상점 이름", example = "김밥천국")
        private String storeName;

        @Schema(description = "메뉴 목록")
        private List<MenuResponse> menus;

        @Data
        @Schema(description = "메뉴 정보 DTO")
        public static class MenuResponse {

            @Schema(description = "메뉴 이름", example = "참치김밥")
            private String menuName;

            @Schema(description = "주문 수량", example = "2")
            private Integer quantity;

            @Schema(description = "메뉴 총 가격", example = "10000")
            private Integer menuTotalPrice;

            @Schema(description = "옵션 포함 여부", example = "true")
            private Boolean hasOption;

            @Schema(description = "옵션 목록")
            private List<OptionResponse> options;

            @Data
            @Schema(description = "옵션 정보 DTO")
            public static class OptionResponse {

                @Schema(description = "옵션 ID", example = "1")
                private Long optionId;

                @Schema(description = "옵션 이름", example = "치즈 추가")
                private String optionName;

                @Schema(description = "옵션 가격", example = "1000")
                private Integer optionPrice;
            }
        }
    }
}
