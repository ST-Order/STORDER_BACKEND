package com.storder.order.menu.docs.seller;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.menu.exception.MenuErrorCode;
import com.storder.order.menu.exception.MenuException;
import com.storder.order.store.exception.StoreErrorCode;
import com.storder.order.store.exception.StoreException;

@ExceptionDoc
public class SellerGetMenuExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 액세스_토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);

    @ExplainError
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError
    public GlobalCodeException 메뉴_관리_권한_없음 = new MenuException(MenuErrorCode.NOT_MENU_OWNER);

    @ExplainError
    public GlobalCodeException 가게_존재하지_않음 = new StoreException(StoreErrorCode.STORE_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 메뉴_존재하지_않음 = new MenuException(MenuErrorCode.MENU_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 메뉴_조회_실패 = new MenuException(MenuErrorCode.MENU_DATABASE_ERROR);
}
