package com.storder.order.store.exception;

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
public enum StoreErrorCode implements BaseErrorCode {

    @ExplainError("오픈 상태가 유효하지 않을 경우 발생하는 오류입니다.")
    INVALID_OPEN_STATUS(BAD_REQUEST, "STORE_400_1", "잘못된 오픈 상태 값입니다."),

    @ExplainError("식당 이미지의 url형식이 유효하지 않을 경우 발생하는 오류입니다.")
    INVALID_STORE_IMAGE_URL(BAD_REQUEST, "STORE_400_2", "잘못된 식당 이미지 url입니다."),

    @ExplainError("가게 오픈/마감 상태 변경 요청 시 이미 해당 상태일 경우 발생하는 오류입니다.")
    ALREADY_OPEN_STATUS(BAD_REQUEST, "STORE_400_3", "이미 해당 오픈 상태입니다."),

    @ExplainError("로그인한 사용자가 자신이 소유하지 않은 가게를 수정하려고 할 때 발생하는 오류입니다.")
    NOT_STORE_OWNER(FORBIDDEN, "STORE_403_1", "식당의 소유자가 아닙니다."),

    @ExplainError("식당이 존재하지 않을 경우 발생하는 오류입니다.")
    STORE_NOT_FOUND(NOT_FOUND, "STORE_404_1", "식당을 찾을 수 없습니다."),

    @ExplainError("식당 이미지가 존재하지 않을 경우 발생하는 오류입니다.")
    STORE_IMAGE_NOT_FOUND(NOT_FOUND, "STORE_404_2", "식당 이미지를 찾을 수 없습니다.");

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
