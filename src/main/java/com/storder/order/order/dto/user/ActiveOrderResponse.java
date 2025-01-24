package com.storder.order.order.dto.user;

import com.storder.order.order.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "활성화된 주문 현황 조회 응답 DTO")
@Builder
public class ActiveOrderResponse {

    @Schema(description = "주문 ID", example = "1")
    private Long orderId;

    @Schema(description = "상점 이름", example = "바비든든")
    private String storeName;

    @Schema(description = "상점 이미지", example = "/stores/store1.png")
    private String storeImage;

    @Schema(description = "주문 상태", example = "PREPARING")
    private OrderStatus status;

    @Schema(description = "주문 상세 보기")
    private List<OrderedMenus> menus;

    @Data
    @Schema(description = "주문된 메뉴 정보 DTO")
    public static class OrderedMenus {
        @Schema(description = "메뉴 이름", example = "제육덮밥")
        private String menuName;

        @Schema(description = "메뉴 이미지", example = "/menus/menu1.png")
        private String menuImage;

        @Schema(description = "옵션 목록")
        private List<String> options;

        @Schema(description = "주문 수량", example = "1")
        private Integer quantity;

        @Schema(description = "메뉴 총 가격", example = "8000")
        private Integer menuTotalPrice;
    }
}
