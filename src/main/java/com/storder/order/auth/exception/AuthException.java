package com.storder.order.auth.exception;

import com.storder.order.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class AuthException extends GlobalCodeException {

    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}
