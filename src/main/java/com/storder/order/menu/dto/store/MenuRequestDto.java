package com.storder.order.menu.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRequestDto {

    @Schema(description = "메뉴 이름", example = "김치찌개")
    private String menuName;

    @Schema(description = "메뉴 이미지 URL", example = "https://example.com/images/kimchi.png")
    private String menuImage;

    @Schema(description = "메뉴 설명", example = "매콤한 김치찌개")
    private String description;

    @Schema(description = "메뉴 가격", example = "8000")
    private int price;

    @Schema(description = "옵션 리스트")
    private List<OptionDto> options;

    @Getter
    @Builder
    public static class OptionDto {
        @Schema(description = "옵션 이름", example = "밥 추가")
        private String optionName;

        @Schema(description = "옵션 가격", example = "1000")
        private int optionPrice;

        @Schema(description = "옵션 가능 여부", example = "true")
        private boolean optionAvailable;
    }

    @Getter
    @Builder
    public static class SoldOutStatusRequest {
        @Schema(description = "품절 상태", example = "true")
        private boolean isSoldOut;
    }
}
