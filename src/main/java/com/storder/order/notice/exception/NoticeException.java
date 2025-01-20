package com.storder.order.notice.exception;

import com.storder.order.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class NoticeException extends GlobalCodeException {

    public NoticeException(NoticeErrorCode errorCode) {
        super(errorCode);
    }
}
