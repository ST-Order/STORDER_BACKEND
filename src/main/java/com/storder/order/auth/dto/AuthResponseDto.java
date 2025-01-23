package com.storder.order.auth.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class AuthResponseDto {

	@Getter
	@Builder
	public static class EmailVerification {
		@Schema(description = "이메일 인증 성공 여부", example = "true")
		private boolean success;
		@Schema(description = "대학명", example = "서울과학기술대학교")
		private String univName;
		@Schema(description = "인증받은 이메일", example = "orieasy1@seoultech.ac.kr")
		private String certifiedEmail;
		@Schema(description = "인증받은 시간", example = "2023-01-03T09:30:22")
		private LocalDateTime certifiedDate;
	}

	@Getter
	@Builder
	public static class TokenInfo {
		@Schema(description = "이름", example = "이지원")
		private String name;
		@Schema(description = "액세스 토큰", example = "eyJh~~")
		private String accessToken;
		@Schema(description = "액세스 토큰 만료일자", example = "2024-07-27T14:25:39")
		private LocalDateTime accessTokenExpiration;
		@Schema(description = "리프레시 토큰", example = "eyJh~~")
		private String refreshToken;
		@Schema(description = "리프레시 토큰 만료일자", example = "2024-07-27T14:25:39")
		private LocalDateTime refreshTokenExpiration;
	}

}
