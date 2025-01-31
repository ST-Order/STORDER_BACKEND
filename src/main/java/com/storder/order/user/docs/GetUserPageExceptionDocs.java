package com.storder.order.user.docs;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.user.exception.UserErrorCode;

@ExceptionDoc
public class GetUserPageExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 액세스_토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);

    @ExplainError
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError
    public GlobalCodeException 유저_정보_또는_구매_내역_조회_불가 =
            new GlobalCodeException(UserErrorCode.USER_INFO_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 주문_내역_조회_불가 =
            new GlobalCodeException(UserErrorCode.ORDER_HISTORY_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 주문_상세_정보_조회_불가 =
            new GlobalCodeException(UserErrorCode.ORDER_DETAIL_NOT_FOUND);
}
