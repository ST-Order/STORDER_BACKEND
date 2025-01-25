package com.storder.order.order.dto.seller;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "완료된 주문 처리 내역 조회 응답 DTO")
@Builder
public class FinishedOrderListResponse {

    @Schema(description = "일일매출액", example = "7600000")
    private Integer dailyTotalSales;

    @Schema(description = "주문 목록")
    private List<FinishedOrderList> orders;

    @Data
    @Schema(description = "완료된 주문 목록 DTO")
    public static class FinishedOrderList {

        @Schema(description = "주문 ID", example = "1")
        private Long orderId;

        @Schema(description = "주문 시간", example = "2021-08-01 12:00:00")
        private String orderTime;

        @Schema(description = "주문 개수", example = "3")
        private Integer orderCount;

        @Schema(description = "총 주문 가격", example = "15000")
        private Integer totalPrice;

        @Schema(description = "주문 메뉴 목록")
        private OrderMenuList orderMenus;

        @Data
        @Schema(description = "주문 메뉴 목록 DTO")
        public static class OrderMenuList {

            @Schema(description = "메뉴 이름", example = "제육덮밥")
            private String menuName;

            @Schema(description = "메뉴 수량", example = "2")
            private Integer quantity;

            @Schema(description = "메뉴 옵션", example = "밥추가")
            private List<String> options;
        }
    }
}
