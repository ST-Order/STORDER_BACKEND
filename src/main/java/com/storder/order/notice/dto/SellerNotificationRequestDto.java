package com.storder.order.notice.dto;

import com.storder.order.notice.entity.NoticeCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class SellerNotificationRequestDto {

	@Getter
	@Builder
	public static class Creation {
		@Schema(description = "제목", example = "신메뉴 제육덮밥 출시")
		private String title;
		@Schema(description = "카테고리", example = "NEW_MENU_RELEASE")
		private NoticeCategory category;
		@Schema(description = "게시글 사진 url", example = "https://example.com/images/notification.jpg")
		private String imageUrl;
		@Schema(description = "내용", example = "신메뉴 출시 완료!")
		private String content;
		@Schema(description = "게시 기간", example = "7(일주일)")
		private int visibilityPeriod;
	}

}
