package com.storder.order.pay.exception;

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
public enum PayErrorCode implements BaseErrorCode {
    @ExplainError("잘못된 결제수단 입력일 경우 발생하는 에러입니다.")
    INVALID_PAYMENT_METHOD(BAD_REQUEST, "PAY_400_1", "잘못된 결제 수단입니다."),

    @ExplainError("유효하지 않은 금액일 경우 발생하는 에러입니다.")
    INVALID_PAYMENT_AMOUNT(BAD_REQUEST, "PAY_400_2", "잘못된 결제 금액입니다."),

    @ExplainError("결제 건이 존재하지 않을 경우 발생하는 오류입니다.")
    PAYMENT_NOT_FOUND(NOT_FOUND, "PAY_404_1", "해당 결제건이 존재하지 않습니다."),

    @ExplainError("잘못된 결제 이벤트의 상태일 경우 발생하는 에러입니다.")
    INVALID_OUTBOX_STATUS(CONFLICT, "PAY_409_1", "잘못된 결제 outbox 상태입니다."),

    @ExplainError("잘못된 결제 상태일 경우 발생하는 에러입니다.")
    INVALID_PAYMENT_STATUS(CONFLICT, "PAY_409_2", "잘못된 결제 상태입니다."),

    @ExplainError("Payload 직렬화 중 예외가 발생한 경우의 에러입니다.")
    PAYLOAD_SERIALIZATION_ERROR(INTERNAL_SERVER, "PAY_500_1", "Payload 직렬화에 실패했습니다.");

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
