package com.storder.order.store.exception;

import com.storder.order.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class StoreException extends GlobalCodeException {

    public StoreException(StoreErrorCode errorCode) {
        super(errorCode);
    }
}
