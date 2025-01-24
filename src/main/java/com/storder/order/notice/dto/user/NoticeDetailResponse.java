package com.storder.order.notice.dto.user;

import com.storder.order.notice.entity.NoticeCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeDetailResponse {

    @Schema(description = "공지사항 ID", example = "1")
    private Long noticeId;

    @Schema(description = "식당이름", example = "바비든든")
    private String storeName;

    @Schema(description = "식당 이미지", example = "/stores/store1.png")
    private String storeImage;

    @Schema(description = "제목", example = "바비든든 신메뉴 제육덮밥 출시")
    private String title;

    @Schema(description = "공지사항 카테고리", example = "신메뉴 출시")
    private NoticeCategory category;

    @Schema(description = "관련 이미지", example = "/images/notice1.png")
    private String image;

    @Schema(
            description = "내용",
            example =
                    "사나이의 맛!! 제육덮밥 출시\n2025년 1월부터 요청이 많았던 제육덮밥을 드디어 출시합니다!!\n바비든든만의 비법소스로 메콤한 돼지고기 제육덮밥을 즐겨보세요")
    private String content;

    @Schema(description = "생성일시", example = "2021-07-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시", example = "2021-07-01T00:00:00")
    private LocalDateTime updatedAt;
}
