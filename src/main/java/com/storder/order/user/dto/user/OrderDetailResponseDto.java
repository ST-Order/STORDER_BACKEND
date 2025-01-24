package com.storder.order.user.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDetailResponseDto {

    @Schema(description = "주문 번호")
    private int orderId;

    @Schema(description = "주문 시간", example = "2024-10-04T18:03:00")
    private LocalDateTime orderTime;

    @Schema(description = "매장 이름", example = "경성카츠")
    private String storeName;

    @Schema(description = "주문된 메뉴들")
    private List<MenuDto> menu;

    @Schema(description = "총 가격", example = "18000")
    private int totalPrice;

    @Schema(description = "결제 방법", example = "KAKAOPAY")
    private String paymentMethod;

    @Schema(description = "결제 시간", example = "2024-10-04T18:02:30")
    private LocalDateTime paymentTime;

    @Getter
    @Builder
    public static class MenuDto {

        @Schema(description = "메뉴 이미지", example = "https://example.com/images/menu1.jpg")
        private String menuImage;

        @Schema(description = "메뉴 이름", example = "경양식 돈까스")
        private String menuName;

        @Schema(description = "메뉴 옵션들")
        private List<OptionDto> option;

        @Getter
        @Builder
        public static class OptionDto {
            @Schema(description = "옵션 이름", example = "밥 추가")
            private String optionName;
        }
    }
}
