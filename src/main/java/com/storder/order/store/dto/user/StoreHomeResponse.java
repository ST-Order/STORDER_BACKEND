package com.storder.order.store.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "상점 홈 조회 응답 DTO")
@Builder
public class StoreHomeResponse {

    @Schema(description = "유저 정보")
    private UserInfo user;

    @Schema(description = "상점 정보 목록")
    private List<StoreInfo> stores;

    @Schema(description = "공지 사항 목록")
    private List<NoticeInfo> notices;

    @Data
    @Schema(description = "유저 정보 DTO")
    public static class UserInfo {
        @Schema(description = "유저 ID", example = "1")
        private Long userId;

        @Schema(description = "유저 이름", example = "희진")
        private String username;
    }

    @Data
    @Schema(description = "상점 정보 DTO")
    public static class StoreInfo {
        @Schema(description = "상점 ID", example = "1")
        private Long storeId;

        @Schema(description = "상점 이름", example = "바비든든")
        private String storeName;

        @Schema(description = "상점 이미지", example = "/stores/store1.png")
        private String storeImage;
    }

    @Data
    @Schema(description = "공지사항 정보 DTO")
    @Builder
    public static class NoticeInfo {
        @Schema(description = "공지사항 ID", example = "1")
        private Long noticeId;

        @Schema(description = "상점 이름", example = "바비든든")
        private String storeName;

        @Schema(description = "상점 이미지", example = "/stores/store1.png")
        private String storeImage;

        @Schema(description = "공지 제목", example = "제육덮밥 출시")
        private String title;

        @Schema(description = "공지 카테고리", example = "신메뉴 출시")
        private String category;

        @Schema(description = "공지 생성일", example = "2024-10-13T15:00:00")
        private LocalDateTime createdAt;
    }
}
