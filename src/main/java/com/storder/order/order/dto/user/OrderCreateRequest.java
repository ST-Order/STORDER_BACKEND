package com.storder.order.order.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "주문 생성 요청 DTO")
@Builder
public class OrderCreateRequest {

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "사용자 이름", example = "홍길동")
    private String userName;

    @Schema(description = "주문 목록")
    private List<OrderRequest> orders;

    @Schema(description = "총 가격", example = "8000")
    private Integer totalPrice;

    @Data
    @Schema(description = "상점별 주문 DTO")
    public static class OrderRequest {

        @Schema(description = "상점 ID", example = "1")
        private Long storeId;

        @Schema(description = "상점 이름", example = "바비든든")
        private String storeName;

        @Schema(description = "메뉴 목록")
        private List<MenuRequest> menus;

        @Data
        @Schema(description = "메뉴 정보 DTO")
        public static class MenuRequest {

            @Schema(description = "메뉴 이름", example = "제육덮밥")
            private String menuName;

            @Schema(description = "옵션 목록")
            private List<OptionRequest> options;

            @Schema(description = "주문 수량", example = "1")
            private Integer quantity;

            @Schema(description = "메뉴 총 가격", example = "8000")
            private Integer menuTotalPrice;

            @Data
            @Schema(description = "옵션 정보 DTO")
            public static class OptionRequest {

                @Schema(description = "옵션 ID", example = "1")
                private Long optionId;

                @Schema(description = "옵션 이름", example = "밥 추가")
                private String optionName;

                @Schema(description = "옵션 가격", example = "1000")
                private Integer optionPrice;

                @Schema(description = "옵션 사용 가능 여부", example = "true")
                private Boolean optionAvailable;
            }
        }
    }
}
