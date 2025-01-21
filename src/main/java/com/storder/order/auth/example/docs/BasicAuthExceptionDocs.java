package com.storder.order.auth.example.docs;

import com.storder.order.auth.example.exception.AuthErrorCode;
import com.storder.order.auth.example.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class BasicAuthExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("엑세스 토큰이 만료된 경우 발생합니다.")
    public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError("엑세스 토큰이 유효하지 않은 경우 발생합니다.")
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError("엑세스 토큰이 없는 경우 발생합니다.")
    public GlobalCodeException 토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);
}
