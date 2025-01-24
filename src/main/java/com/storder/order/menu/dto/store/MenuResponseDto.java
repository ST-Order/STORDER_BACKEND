package com.storder.order.menu.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuResponseDto {

    @Schema(description = "메뉴 리스트")
    private List<MenuDto> menus;

    @Getter
    @Builder
    public static class MenuDto {
        @Schema(description = "메뉴 ID", example = "1")
        private Long menuId;

        @Schema(description = "메뉴 이름", example = "삼겹덮밥")
        private String menuName;

        @Schema(description = "베스트 메뉴 여부", example = "true")
        private boolean isBest;

        @Schema(description = "인기 메뉴 여부", example = "true")
        private boolean isPopular;

        @Schema(description = "메뉴 이미지 URL", example = "https://example.com/menu1.png")
        private String menuImage;

        @Schema(description = "메뉴 설명", example = "맛난 삼겹살과 쌈장으로 만든 덮밥")
        private String description;

        @Schema(description = "메뉴 가격", example = "5000")
        private int price;

        @Schema(description = "품절 여부", example = "false")
        private boolean isSoldOut;

        @Schema(description = "판매 가능 여부", example = "true")
        private boolean isAvailable;

        @Schema(description = "옵션 리스트")
        private List<OptionDto> options;

        @Getter
        @Builder
        public static class OptionDto {
            @Schema(description = "옵션 ID", example = "101")
            private Long optionId;

            @Schema(description = "옵션 이름", example = "밥 추가")
            private String optionName;

            @Schema(description = "옵션 가격", example = "500")
            private int optionPrice;

            @Schema(description = "옵션 가능 여부", example = "true")
            private boolean optionAvailable;
        }
    }
}
