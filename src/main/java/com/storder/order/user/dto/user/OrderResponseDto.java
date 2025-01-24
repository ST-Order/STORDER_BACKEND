package com.storder.order.user.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {

    @Schema(description = "주문 리스트")
    private List<OrderDto> orders;

    @Getter
    @Builder
    public static class OrderDto {
        @Schema(description = "메뉴 이미지", example = "https://example.com/images/food1.jpg")
        private String menuImage;

        @Schema(description = "주문 시간", example = "2024-10-04T18:03:00")
        private LocalDateTime orderTime;

        @Schema(description = "주문 상태", example = "COOKING")
        private String orderStatus;

        @Schema(description = "메뉴 이름", example = "경성카츠")
        private String menuName;

        @Schema(description = "메뉴 가격", example = "9000")
        private int price;
    }
}
