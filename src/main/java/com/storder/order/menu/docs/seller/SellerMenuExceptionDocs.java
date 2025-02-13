package com.storder.order.menu.docs.seller;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.menu.exception.MenuErrorCode;
import com.storder.order.menu.exception.MenuException;

@ExceptionDoc
public class SellerMenuExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 액세스_토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);

    @ExplainError
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError
    public GlobalCodeException 메뉴_관리_권한_없음 = new MenuException(MenuErrorCode.NOT_MENU_OWNER);

    @ExplainError
    public GlobalCodeException 해당하는_메뉴_없음 = new MenuException(MenuErrorCode.MENU_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 메뉴_이름_유효하지_않음 = new MenuException(MenuErrorCode.INVALID_MENU_NAME);

    @ExplainError
    public GlobalCodeException 가격_유효하지_않음 = new MenuException(MenuErrorCode.INVALID_MENU_PRICE);

    @ExplainError
    public GlobalCodeException 메뉴_설명_초과 = new MenuException(MenuErrorCode.INVALID_MENU_DESCRIPTION);

    @ExplainError
    public GlobalCodeException 메뉴_이름_중복 = new MenuException(MenuErrorCode.DUPLICATE_MENU_NAME);

    @ExplainError
    public GlobalCodeException 메뉴_등록_실패 = new MenuException(MenuErrorCode.MENU_DATABASE_ERROR);
}
