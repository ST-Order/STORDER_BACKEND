package com.storder.order.notice.exception;

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
public enum NoticeErrorCode implements BaseErrorCode {
    @ExplainError("공지사항 카테고리가 유효하지 않을 경우 발생하는 오류입니다.")
    INVALID_NOTICE_CATEGORY(BAD_REQUEST, "NOTICE_400_1", "잘못된 공지사항 카테고리입니다."),

    @ExplainError("공지사항 노출 시간이 유효하지 않을 경우 발생하는 오류입니다.")
    INVALID_NOTICE_DISPLAY_TIME(BAD_REQUEST, "NOTICE_400_2", "잘못된 공지사항 노출 시간입니다."),

    @ExplainError("공지사항 제목이 비어있을 경우 발생하는 오류입니다.")
    EMPTY_NOTICE_TITLE(BAD_REQUEST, "NOTICE_400_3", "공지사항 제목이 비어있습니다."),

    @ExplainError("공지사항 내용이 비어있을 경우 발생하는 오류입니다.")
    EMPTY_NOTICE_CONTENT(BAD_REQUEST, "NOTICE_400_4", "공지사항 내용이 비어있습니다."),

    @ExplainError("이미 삭제된 공지사항을 다시 삭제하려할 때 발생하는 오류입니다.")
    ALREADY_DELETED_NOTICE(BAD_REQUEST, "NOTICE_400_5", "이미 삭제된 공지사항입니다."),

    @ExplainError("공지사항 작성 날짜가 미래인 경우 발생하는 오류입니다.")
    NOTICE_FUTURE_DATE(BAD_REQUEST, "NOTICE_400_6", "공지사항 작성 날짜는 미래일 수 없습니다."),

    @ExplainError("공지사항 노출 기간이 만료된 경우 발생하는 오류입니다.")
    NOTICE_EXPIRED(BAD_REQUEST, "NOTICE_400_7", "공지사항의 노출 기간이 만료되었습니다."),

    @ExplainError("로그인한 사용자가 해당 공지사항을 수정할 권한이 없을 때 발생하는 오류입니다.")
    NOT_NOTICE_OWNER(FORBIDDEN, "NOTICE_403_1", "해당 공지사항의 수정 권한이 없습니다."),

    @ExplainError("로그인한 사용자가 해당 공지사항을 삭제할 권한이 없을 때 발생하는 오류입니다.")
    NOT_NOTICE_OWNER_DELETE(FORBIDDEN, "NOTICE_403_2", "해당 공지사항의 삭제 권한이 없습니다."),

    @ExplainError("공지사항이 존재하지 않을 경우 발생하는 오류입니다.")
    NOTICE_NOT_FOUND(NOT_FOUND, "NOTICE_404_1", "해당 공지사항이 존재하지 않습니다.");


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
