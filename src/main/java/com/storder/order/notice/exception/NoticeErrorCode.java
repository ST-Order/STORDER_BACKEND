package com.storder.order.notice.exception;

import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.dto.ErrorReason;
import com.storder.order.global.exception.BaseErrorCode;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.storder.order.global.consts.StorderStatic.*;

@Getter
@AllArgsConstructor
public enum NoticeErrorCode implements BaseErrorCode {

    @ExplainError("공지사항 카테고리가 유효하지 않을 경우 발생하는 오류입니다.")
    INVALID_NOTICECATEGORY(BAD_REQUEST, "NOTICE_400_1", "잘못된 공지사항 카테고리입니다.");

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
