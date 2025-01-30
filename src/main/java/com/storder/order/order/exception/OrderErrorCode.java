package com.storder.order.order.exception;

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
public enum OrderErrorCode implements BaseErrorCode {

    @ExplainError("주문 상태가 유효하지 않을 경우 발생하는 오류입니다.")
    INVALID_ORDER_STATUS(BAD_REQUEST, "ORDER_400_1", "잘못된 주문 상태 값입니다."),

    @ExplainError("유효하지 않은 주문 상태 변경 요청일 때 발생하는 오류입니다.")
    INVALID_ORDER_STATUS_CHANGE(BAD_REQUEST, "ORDER_400_2", "유효하지 않은 주문 상태 변경 요청입니다. 주문 상태는 UNPAY -> PAY -> COOKING -> READY 순으로 변경됩니다."),

    @ExplainError("주문한 메뉴 수량이 0 이하일 때 발생하는 오류입니다.")
    INVALID_ORDER_QUANTITY(BAD_REQUEST, "ORDER_400_3", "주문한 메뉴 수량이 0 이하입니다."),

    @ExplainError("로그인한 사장님이 해당 상점 주문을 처리할 권한이 없을 때 발생하는 에러입니다.")
    INVALID_ORDER_OWNER(FORBIDDEN, "ORDER_403_1", "해당 주문의 처리 권한이 없는 사용자입니다."),

    @ExplainError("주문이 존재하지 않을 경우 발생하는 오류입니다.")
    ORDER_NOT_FOUND(NOT_FOUND, "ORDER_404_1", "해당 주문이 존재하지 않습니다."),

    @ExplainError("이미 완료된 주문(상태가 READY인 주문)을 다시 처리하려할 때 발생하는 오류입니다.")
    ORDER_ALREADY_COMPLETED(CONFLICT, "ORDER_409_1", "이미 처리 완료된 주문입니다."),

    @ExplainError("결제되지 않은 주문을 처리하려할 때 발생하는 오류입니다.")
    ORDER_NOT_PAID(CONFLICT, "ORDER_409_2", "결제되지 않은 주문입니다."),

    @ExplainError("같은 주문이 중복으로 생성되었을 때 발생하는 오류입니다.")
    DUPLICATE_ORDER(CONFLICT, "ORDER_409_3", "중복된 주문이 존재합니다.");

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
