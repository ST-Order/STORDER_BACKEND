package com.storder.order.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.regex.Pattern;
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

        @Schema(description = "비밀번호(영문 4~16자, 특수문자 1개 이상 포함)", example = "test1234@@!")
        private String password;

        @Schema(description = "비밀번호 확인", example = "test1234@@!")
        private String passwordCheck;

        @Schema(description = "역할", example = "STUDENT")
        private String role;

        @JsonIgnore
        public boolean isPasswordValid() {
            String PASSWORD_REGEX =
                    "^(?=.*[A-Za-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.{4,16}$)[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$";
            Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

            return PASSWORD_PATTERN.matcher(password).matches();
        }

        @JsonIgnore
        public boolean isNotPasswordCheckValid() {
            if (!passwordCheck.equals(password)) {
                return true;
            }

            return false;
        }
    }

    @Getter
    @Builder
    public static class EmailVerification {
        @Schema(description = "이메일", example = "orieasy1@seoultech.ac.kr")
        private String email;

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

        @JsonIgnore
        public boolean notEqualPassword(String password) {
            if (!this.password.equals(password)) {
                return true;
            }

            return false;
        }
    }
}
