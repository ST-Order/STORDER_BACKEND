package com.storder.order.auth.example.docs;

import com.storder.order.auth.example.exception.AuthErrorCode;
import com.storder.order.auth.example.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class RefreshAuthExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("리프레시 토큰이 만료된 경우 발생합니다.")
    public GlobalCodeException 리프레시_토큰_만료 = new AuthException(AuthErrorCode.REFRESH_TOKEN_EXPIRED);

    @ExplainError("리프레시 토큰이 유효하지 않은 경우 발생합니다.")
    public GlobalCodeException 리프레시_토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);
}
