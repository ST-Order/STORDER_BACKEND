package com.storder.order.auth.example.docs;

import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.exception.GlobalErrorCode;
import com.storder.order.global.exception.GlobalException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class LoginAuthExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("로그인 서버에 보내는 요청에 실패한 경우 발생합니다.")
    public GlobalCodeException 서버_요청_오류 =
            new GlobalException(GlobalErrorCode.OTHER_SERVER_BAD_REQUEST);

    @ExplainError("카카오 서버에 보낸 토큰이 만료된 경우 발생합니다.")
    public GlobalCodeException 서버_토큰_만료 =
            new GlobalException(GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
}
