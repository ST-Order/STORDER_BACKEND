package com.storder.order.store.docs;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.store.exception.StoreErrorCode;
import com.storder.order.store.exception.StoreException;

@ExceptionDoc
public class SellerStoreOpenExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 액세스_토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);

    @ExplainError public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 유효하지_않은_토큰 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError
    public GlobalCodeException 사장님_아님 = new StoreException(StoreErrorCode.NOT_STORE_OWNER);

    @ExplainError
    public GlobalCodeException 식당_존재하지_않음 = new StoreException(StoreErrorCode.STORE_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 이미_오픈된_식당 = new StoreException(StoreErrorCode.ALREADY_OPENED);

    @ExplainError
    public GlobalCodeException 이미_닫힌_식당 = new StoreException(StoreErrorCode.ALREADY_CLOSED);
}
