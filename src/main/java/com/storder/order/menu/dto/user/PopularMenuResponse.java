package com.storder.order.menu.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "인기 메뉴 조회 응답 DTO")
@Builder
public class PopularMenuResponse {

    @Schema(description = "식당 이름", example = "바비든든")
    private String storeName;

    @Schema(description = "메뉴 이름", example = "제육덮밥")
    private String menuName;

    @Schema(description = "메뉴 이미지", example = "/menus/menu1.png")
    private String menuImage;

    @Schema(description = "메뉴 설명", example = "맼모한 제육과 밥을 한번에!")
    private String description;

    @Schema(description = "가격", example = "5000")
    private Integer price;
}
