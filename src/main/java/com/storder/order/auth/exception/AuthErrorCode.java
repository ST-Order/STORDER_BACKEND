package com.storder.order.auth.exception;

import static com.storder.order.global.consts.StorderStatic.*;

import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.dto.ErrorReason;
import com.storder.order.global.exception.BaseErrorCode;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    @ExplainError("univcert 인증 메일 전송시 발생하는 오류입니다.")
    SEND_CERTIFICATION_CODE_ERROR(BAD_REQUEST, "AUTH_400_1", "이미 인증번호를 보낸 메일입니다."),
    @ExplainError("univcert 인증번호 확인시 발생하는 오류입니다.")
    VERIFY_CERTIFICATION_CODE_ERROR(BAD_REQUEST, "AUTH_400_2", "일치하지 않는 인증코드입니다."),
    @ExplainError("비밀번호 형식이 맞지 않으면 발생하는 오류입니다.")
    NOT_VALID_PASSWORD(BAD_REQUEST, "AUTH_400_3", "비밀번호는 영문 4~16자, 특수문자를 1개 이상 포함해야 합니다."),
    @ExplainError("비밀번호와 비밀번호 확인이 일치하지 않으면 발생하는 오류입니다.")
    NOT_EQUAL_PASSWORD_AND_PASSWORD_CHECK(BAD_REQUEST, "AUTH_400_4", "비밀번호가 일치하지 않습니다."),
    @ExplainError("로그인 시 이메일이 일치하지 않으면 발생하는 오류입니다.")
    NOT_EXIST_LOGIN_EMAIL(BAD_REQUEST, "AUTH_400_5", "가입하지 않은 이메일이거나 잘못된 비밀번호입니다."),
    @ExplainError("로그인 시 비밀번호가 일치하지 않으면 발생하는 오류입니다.")
    NOT_EQUAL_PASSWORD(BAD_REQUEST, "AUTH_400_6", "가입하지 않은 이메일이거나 잘못된 비밀번호입니다."),
    @ExplainError("accessToken이 Bearer로 시작하지 않으면 발생하는 오류입니다.")
    NOT_START_WITH_BEARER(BAD_REQUEST, "AUTH_400_7", "accessToken은 Bearer로 시작해야합니다."),
    @ExplainError("회원가입 시 이메일이 기존에 가입되어 있으면 발생하는 오류입니다.")
    ALREADY_SIGN_UP_EMAIL(BAD_REQUEST, "AUTH_400_8", "이미 가입한 이메일입니다."),

    @ExplainError("accessToken 만료시 발생하는 오류입니다.")
    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_401_1", "인증 시간이 만료되었습니다. 인증토큰을 재 발급 해주세요"),
    @ExplainError("인증 토큰이 잘못됐을 때 발생하는 오류입니다.")
    INVALID_TOKEN(UNAUTHORIZED, "AUTH_401_2", "잘못된 토큰입니다. 재 로그인 해주세요"),
    @ExplainError("회원가입 시 이메일이 존재하지 않을 때 발생하는 오류입니다")
    NOT_EXIST_VERIFIED_EMAIL(UNAUTHORIZED, "AUTH_401_3", "해당 이메일이 존재하지 않습니다."),

    @ExplainError("refreshToken 만료시 발생하는 오류입니다.")
    REFRESH_TOKEN_EXPIRED(FORBIDDEN, "AUTH_403_1", "인증 시간이 만료되었습니다. 재 로그인 해주세요."),
    @ExplainError("헤더에 올바른 accessToken을 담지않았을 때 발생하는 오류(형식 불일치 등)")
    ACCESS_TOKEN_NOT_EXIST(FORBIDDEN, "AUTH_403_2", "알맞은 accessToken을 넣어주세요."),

    @ExplainError("이메일이 DB에 존재하지 않으면 발생하는 오류입니다.")
    NOT_EXIST_EMAIL(INTERNAL_SERVER, "AUTH_500_1", "이메일이 존재하지 않습니다."),
    ;

    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}
