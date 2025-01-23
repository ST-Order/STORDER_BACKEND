package com.storder.order.user.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {

    @Schema(description = "이미지 URL", example = "imageURL~~")
    private String imageURL;

    @Schema(description = "별점", example = "3")
    private Integer starPoint;

    @Schema(description = "리뷰 내용", example = "경양식 돈까스 강추합니다!")
    private String content;

    @Schema(description = "메뉴 ID", example = "5")
    private Long menuId;
}
