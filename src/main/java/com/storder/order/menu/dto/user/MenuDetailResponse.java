package com.storder.order.menu.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "메뉴 상세 조회 응답 DTO")
@Builder
public class MenuDetailResponse {

    @Schema(description = "메뉴 ID", example = "1")
    private Long menuId;

    @Schema(description = "메뉴 이름", example = "제육덮밥")
    private String menuName;

    @Schema(description = "메뉴 이미지", example = "/menus/menu1.png")
    private String menuImage;

    @Schema(description = "메뉴 별점", example = "4.5")
    private Double menuRating;

    @Schema(description = "대표 메뉴 여부", example = "true")
    private Boolean isBest;

    @Schema(description = "인기 메뉴 여부", example = "true")
    private Boolean isPopular;

    @Schema(description = "메뉴 설명", example = "맼모한 제육과 밥을 한번에!")
    private String description;

    @Schema(description = "가격", example = "5000")
    private Integer price;

    @Schema(description = "품절여부", example = "false")
    private Boolean isSoldOut;

    @Schema(description = "주문 가능 여부", example = "true")
    private Boolean isAvailable;

    @Schema(description = "메뉴 옵션 목록")
    private List<Options> options;

    @Data
    @Schema(description = "메뉴 옵션 DTO")
    public static class Options {

        @Schema(description = "옵션 ID", example = "1")
        private Long optionId;

        @Schema(description = "옵션 이름", example = "계란후라이")
        private String optionName;

        @Schema(description = "옵션 가격", example = "500")
        private Integer optionPrice;

        @Schema(description = "옵션 선택 가능 여부", example = "true")
        private Boolean OptionAvailable;

        @Builder
        public Options(
                Long optionId, String optionName, Integer optionPrice, Boolean OptionAvailable) {
            this.optionId = optionId;
            this.optionName = optionName;
            this.optionPrice = optionPrice;
            this.OptionAvailable = OptionAvailable;
        }
    }
}
