package com.storder.order.menu.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRequestDto {

    @Schema(description = "메뉴 이름", example = "오삼불고기덮밥")
    private String menuName;

    @Schema(description = "메뉴 이미지 URL", example = "https://example.com/images/bulgogi.png")
    private String menuImage;

    @Schema(description = "메뉴 설명", example = "쫄깃한 오징어와 삼겹살로 만든 매콤한 불고기 덮밥")
    private String description;

    @Schema(description = "메뉴 가격", example = "3500")
    private int price;

    @Schema(description = "베스트 메뉴 여부", example = "true")
    private boolean isBest;

    @Schema(description = "인기 메뉴 여부", example = "true")
    private boolean isPopular;

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
