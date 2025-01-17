package com.storder.order.pay.exception;

import com.storder.order.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class PayException extends GlobalCodeException {

    public PayException(PayErrorCode errorCode) {
        super(errorCode);
    }

}
