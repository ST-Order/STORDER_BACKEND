package com.storder.order.notice.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "공지사항 목록 조회 응답 DTO")
@Builder
public class NoticeListResponse {

    @Schema(description = "공지사항 ID", example = "1")
    private Long noticeId;

    @Schema(description = "식당이름", example = "바비든든")
    private String storeName;

    @Schema(description = "식당 이미지", example = "/stores/store1.png")
    private String storeImage;

    @Schema(description = "제목", example = "바비든든 신메뉴 제육덮밥 출시")
    private String title;

    @Schema(description = "공지사항 카테고리", example = "신메뉴 출시")
    private String category;

    @Schema(description = "생성일시", example = "2021-07-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시", example = "2021-07-01T00:00:00")
    private LocalDateTime updatedAt;
}
