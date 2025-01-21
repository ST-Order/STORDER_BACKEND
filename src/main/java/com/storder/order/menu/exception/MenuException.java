package com.storder.order.menu.exception;

import com.storder.order.global.exception.GlobalCodeException;
import lombok.Getter;

@Getter
public class MenuException extends GlobalCodeException {

    public MenuException(MenuErrorCode errorCode) {
        super(errorCode);
    }
}
