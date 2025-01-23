package com.storder.order.auth.dto;

import java.util.regex.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class AuthRequestDto {

	@Getter
	@Builder
	public static class SignUp {
		@Schema(description = "유저명", example = "홍길동")
		private String username;
		@Schema(description = "이메일", example = "orieasy1@seoultech.ac.kr")
		private String email;
		@Schema(description = "비밀번호(숫자와 특수문자 포함)", example = "test1234@@!")
		private String password;
		@Schema(description = "비밀번호 확인", example = "test1234@@!")
		private String passwordCheck;
		@Schema(description = "역할", example = "STUDENT")
		private String role;

		private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

		private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$";
		private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

		public boolean isEmailValid() {
			return EMAIL_PATTERN.matcher(email).matches();
		}

		public boolean isPasswordValid() {
			return PASSWORD_PATTERN.matcher(password).matches();
		}

	}

	@Getter
	@Builder
	public static class EmailCertification {
		@Schema(description = "부여받은 API 키", example = "b70a66b3-f1de-307d-14e1-b6c2ac1f8dea")
		private String key;
		@Schema(description = "이메일", example = "orieasy1@seoultech.ac.kr")
		private String email;
		@Schema(description = "대학명", example = "서울과학기술대학교")
		private String univName;
		@Schema(description = "false로 고정, 소유자 인증만", example = "false")
		private boolean univCheck;
	}

	@Getter
	@Builder
	public static class EmailVerification {
		@Schema(description = "부여받은 API 키", example = "b70a66b3-f1de-307d-14e1-b6c2ac1f8dea")
		private String key;
		@Schema(description = "이메일", example = "orieasy1@seoultech.ac.kr")
		private String email;
		@Schema(description = "대학명", example = "서울과학기술대학교")
		private String univName;
		@Schema(description = "인증코드", example = "3816")
		private int code;
	}

	@Getter
	@Builder
	public static class Login {
		@Schema(description = "이메일", example = "orieasy1@seoultech.ac.kr")
		private String email;
		@Schema(description = "비밀번호", example = "test1234@@!")
		private String password;
	}

}
