package com.storder.order.menu.docs;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.menu.exception.MenuErrorCode;
import com.storder.order.menu.exception.MenuException;

@ExceptionDoc
public class SellerGetMenuExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 액세스_토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);

    @ExplainError
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError
    public GlobalCodeException 메뉴_관리_권한_없음 = new MenuException(MenuErrorCode.NOT_MENU_OWNER);
}
