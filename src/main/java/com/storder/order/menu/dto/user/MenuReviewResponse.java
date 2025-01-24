package com.storder.order.menu.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "메뉴 리뷰 조회 응답 DTO")
@Builder
public class MenuReviewResponse {

    @Schema(description = "메뉴 ID", example = "1")
    private Long menuId;

    @Schema(description = "메뉴 이름", example = "제육덮밥")
    private String menuName;

    @Schema(description = "메뉴 이미지", example = "/menus/menu1.png")
    private String menuImage;

    @Schema(description = "메뉴 기격", example = "5000")
    private Integer price;

    @Schema(description = "메뉴 별점", example = "4.5")
    private Double rating;

    @Schema(description = "리뷰 목록")
    private List<Reviews> reviews;

    @Data
    @Schema(description = "리뷰 정보 DTO")
    public static class Reviews {

        @Schema(description = "리뷰 ID", example = "1")
        private Long reviewId;

        @Schema(description = "리뷰 내용", example = "맛있어요!")
        private String content;

        @Schema(description = "별점", example = "4")
        private Double rating;

        @Schema(description = "작성자", example = "홍길동")
        private String writer;

        @Schema(description = "리뷰 작성일", example = "2021-08-01T12:00:00")
        private LocalDateTime createdAt;
    }
}
