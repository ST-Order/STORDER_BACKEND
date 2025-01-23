package com.storder.order.menu.dto.user;

import com.storder.order.store.entity.OpenStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "메뉴 목록 조회 응답 DTO")
@Builder
public class MenuListResponse {

    @Schema(description = "식당 ID", example = "1")
    private Long storeId;

    @Schema(description = "식당 이름", example = "바비든든")
    private String storeName;

    @Schema(description = "식당 오픈 상태", example = "영업중")
    private OpenStatus openStatus;

    @Schema(description = "메뉴 목록")
    private List<Menus> menus;

    @Data
    @Schema(description = "메뉴 정보 DTO")
    public static class Menus {

        @Schema(description = "메뉴 ID", example = "1")
        private Long menuId;

        @Schema(description = "메뉴 이름", example = "제육덮밥")
        private String menuName;

        @Schema(description = "메뉴 이미지", example = "/menus/menu1.png")
        private String menuImage;

        @Schema(description = "메뉴 설명", example = "맼모한 제육과 밥을 한번에!")
        private String description;

        @Schema(description = "가격", example = "5000")
        private Integer price;

        @Schema(description = "대표 메뉴 여부", example = "true")
        private Boolean isBest;

        @Schema(description = "인기 메뉴 여부", example = "true")
        private Boolean isPopular;

        @Schema(description = "품절여부", example = "false")
        private Boolean isSoldOut;

        @Schema(description = "주문 가능 여부", example = "true")
        private Boolean isAvailable;

        @Builder
        public Menus(Long menuId, String menuName, String menuImage, String description, Integer price, Boolean isBest, Boolean isPopular, Boolean isSoldOut, Boolean isAvailable) {
            this.menuId = menuId;
            this.menuName = menuName;
            this.menuImage = menuImage;
            this.description = description;
            this.price = price;
            this.isBest = isBest;
            this.isPopular = isPopular;
            this.isSoldOut = isSoldOut;
            this.isAvailable = isAvailable;
        }
    }
}
