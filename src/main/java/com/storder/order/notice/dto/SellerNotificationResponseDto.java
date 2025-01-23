package com.storder.order.notice.dto;

import java.time.LocalDateTime;

import com.storder.order.notice.entity.NoticeCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class SellerNotificationResponseDto {

	@Getter
	@Builder
	public static class Brief {
		@Schema(description = "공지사항Id", example = "1")
		private Long id;
		@Schema(description = "카테고리", example = "OUT_OF_STOCK")
		private NoticeCategory category;
		@Schema(description = "제목", example = "오늘 삼겹덮밥이 품절되었습니다.")
		private String title;
		@Schema(description = "게시 시각", example = "2024-12-12T16:00:00")
		private LocalDateTime publishedTime;
	}

	@Getter
	@Builder
	public static class Detail {
		@Schema(description = "제목", example = "오늘 삼겹덮밥이 품절되었습니다.")
		private String title;
		@Schema(description = "카테고리", example = "OUT_OF_STOCK")
		private NoticeCategory category;
		@Schema(description = "게시글 사진 url", example = "https://example.com/images/notification.jpg")
		private String imageUrl;
		@Schema(description = "내용", example = "식감 일품!!날치알 덮밥 출시 2024~~~")
		private String content;
		@Schema(description = "게시 시각", example = "2024-12-12T16:00:00")
		private LocalDateTime publishedTime;
	}

}
