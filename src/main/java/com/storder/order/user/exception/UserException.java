package com.storder.order.user.exception;

import com.storder.order.global.exception.GlobalCodeException;

public class UserException extends GlobalCodeException {
    public UserException(UserErrorCode errorCode) {
        super(errorCode);
    }
}
