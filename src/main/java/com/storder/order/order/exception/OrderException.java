package com.storder.order.order.exception;

import com.storder.order.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class OrderException extends GlobalCodeException {

    public OrderException(OrderErrorCode errorCode) {
        super(errorCode);
    }
}
