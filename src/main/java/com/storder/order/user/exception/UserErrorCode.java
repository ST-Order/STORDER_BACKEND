package com.storder.order.user.exception;

import static com.storder.order.global.consts.StorderStatic.*;

import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.dto.ErrorReason;
import com.storder.order.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    @ExplainError("유저 역할이 유효하지 않은 경우 발생하는 오류입니다.")
    INVAILD_USERROLE(BAD_REQUEST, "USER_400_1", "잘못된 유저 역할 값입니다."),

    @ExplainError("비밀번호가 형식에 맞지 않는 경우 발생하는 오류입니다.")
    INVALID_PASSWORD_FORMAT(BAD_REQUEST, "USER_400_2", "비밀번호가 형식에 맞지 않습니다."),

    @ExplainError("비밀번호가 이전 값과 같은 경우 발생하는 오류입니다.")
    PASSWORD_SAME_AS_OLD(BAD_REQUEST, "USER_400_4", "비밀번호가 이전 값과 동일합니다."),

    @ExplainError("비밀번호가 조건과 맞지 않는 경우 발생하는 오류입니다.")
    PASSWORD_CONSTRAINT_VIOLATION(BAD_REQUEST, "USER_400_5", "비밀번호가 요구 조건에 맞지 않습니다."),

    @ExplainError("이미 가입된 이메일인 경우 발생하는 오류입니다.")
    EMAIL_ALREADY_REGISTERED(CONFLICT, "USER_409_2", "이미 등록된 이메일입니다."),

    @ExplainError("사용자가 입력한 인증 코드가 전송된 코드와 일치하지 않는 경우 발생하는 오류입니다.")
    INVALID_VERIFICATION_CODE(BAD_REQUEST, "USER_400_8", "인증코드가 일치하지 않습니다."),

    @ExplainError("인증 코드가 만료된 경우 발생하는 오류입니다.")
    EXPIRATION_VERIFICATION_CODE(BAD_REQUEST, "USER_400_9", "인증코드가 만료 되었습니다."),

    @ExplainError("이메일 형식이 알맞지 않는 경우 발생하는 오류입니다.")
    INVALID_EMAIL(BAD_REQUEST, "USER_400_11", "이메일 형식이 요구 조건에 맞지 않습니다."),

    @ExplainError("기존 비밀번호가 일치하지 않는 경우 발생하는 오류입니다.")
    INVALID_OLD_PASSWORD(BAD_REQUEST, "USER_400_12", "기존 비밀번호가 일치하지 않습니다."),

    @ExplainError("회원이 존재하지 않는 경우 발생하는 오류입니다.")
    USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "해당 회원이 존재하지 않습니다.");

    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() {
        return this.reason;
    }
}
