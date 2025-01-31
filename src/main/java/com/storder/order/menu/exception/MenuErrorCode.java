package com.storder.order.menu.exception;

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
public enum MenuErrorCode implements BaseErrorCode {
    @ExplainError("메뉴 관리에 대한 권한이 없을 경우 발생하는 오류입니다.")
    NOT_MENU_OWNER(FORBIDDEN, "MENU_403_1", "메뉴 관리 권한이 없습니다."),

    @ExplainError("메뉴 등록 요청 시 유효하지 않은 데이터가 포함된 경우 발생하는 오류입니다.")
    INVALID_MENU_DATA(BAD_REQUEST, "MENU_400_1", "유효하지 않은 메뉴 데이터입니다."),

    @ExplainError("메뉴 등록 요청 시 중복된 메뉴명이 존재하는 경우 발생하는 오류입니다.")
    DUPLICATE_MENU_NAME(BAD_REQUEST, "MENU_400_2", "이미 존재하는 메뉴명입니다."),

    @ExplainError("요청에 해당하는 메뉴가 존재하지 않는 경우 발생하는 오류입니다.")
    MENU_NOT_FOUND(NOT_FOUND, "MENU_404_1", "해당 메뉴를 찾을 수 없습니다."),

    @ExplainError("품절 상태 변경 요청이 유효하지 않을 경우 발생하는 오류입니다.")
    INVALID_SOLD_OUT_STATUS_REQUEST(BAD_REQUEST, "MENU_400_3", "유효하지 않은 품절 상태 변경 요청입니다.");

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
