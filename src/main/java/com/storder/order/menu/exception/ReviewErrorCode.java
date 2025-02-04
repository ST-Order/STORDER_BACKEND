package com.storder.order.menu.exception;

import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.dto.ErrorReason;
import com.storder.order.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.storder.order.global.consts.StorderStatic.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    @ExplainError("리뷰 작성 시 유효하지 않은 데이터가 포함된 경우 발생하는 오류입니다.")
    INVALID_REVIEW_DATA(BAD_REQUEST, "REVIEW_400_1", "유효하지 않은 리뷰 데이터입니다."),

    @ExplainError("별점 값이 유효하지 않을 때 발생하는 오류입니다.")
    INVALID_STAR_RATING(BAD_REQUEST, "REVIEW_400_2", "별점은 1.0 ~ 5.0 사이의 값이어야 합니다."),

    @ExplainError("리뷰 내용이 비어있거나 너무 길 경우 발생하는 오류입니다.")
    INVALID_REVIEW_CONTENT(BAD_REQUEST, "REVIEW_400_3", "리뷰 내용은 1000자 이하여야 합니다."),

    @ExplainError("리뷰 작성 시 주문 내역이 없는 유저가 작성할 경우 발생하는 오류입니다.")
    REVIEW_WITHOUT_ORDER(BAD_REQUEST, "REVIEW_400_4", "주문 내역이 없는 유저는 리뷰를 작성할 수 없습니다."),

    @ExplainError("본인의 주문건에 대한 리뷰 작성이 아닐 경우 발생하는 오류입니다.")
    REVIEW_NOT_FROM_USER_ORDER(BAD_REQUEST, "REVIEW_400_5", "본인의 주문에 대해 리뷰를 작성해야 합니다."),

    @ExplainError("리뷰 작성 시 이미 작성된 리뷰가 존재하는 경우 발생하는 오류입니다.")
    DUPLICATE_REVIEW(BAD_REQUEST, "REVIEW_400_6", "이미 작성된 리뷰가 존재합니다."),

    @ExplainError("리뷰 조회 시 사용자 본명을 그대로 사용할 경우 발생하는 오류입니다.")
    USER_NAME_EXPOSED(BAD_REQUEST, "REVIEW_400_7", "리뷰 조회 시 사용자 이름이 그대로 노출될 수 없습니다."),

    @ExplainError("리뷰 작성 날짜가 미래인 경우 발생하는 오류입니다.")
    REVIEW_FUTURE_DATE(BAD_REQUEST, "REVIEW_400_8", "리뷰 작성 날짜는 미래일 수 없습니다."),

    @ExplainError("별점 계산 중 충돌이 발생한 경우 발생하는 오류입니다.")
    STAR_RATING_CONFLICT(BAD_REQUEST, "REVIEW_409_1", "별점 계산 중 충돌이 발생했습니다."),;


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
