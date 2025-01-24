package com.storder.order.menu.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SoldOutMenuResponseDto {

    @Schema(description = "품절 메뉴 리스트")
    private List<MenuDto> menus;

    @Getter
    @Builder
    public static class MenuDto {
        @Schema(description = "메뉴 ID", example = "1")
        private Long menuId;

        @Schema(description = "메뉴 이름", example = "삼겹덮밥")
        private String menuName;

        @Schema(description = "메뉴 이미지 URL", example = "https://example.com/menu1.png")
        private String menuImage;

        @Schema(description = "메뉴 가격", example = "5000")
        private int price;

        @Schema(description = "품절 여부", example = "true")
        private boolean isSoldOut;
    }
}
