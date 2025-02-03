package com.storder.order.menu.exception;

import com.storder.order.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class ReviewException extends GlobalCodeException {

    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode);
    }
}
