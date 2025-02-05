package com.storder.order.auth.docs;

import static com.storder.order.auth.exception.AuthErrorCode.*;

import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class RegisterExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError public GlobalCodeException 비밀번호_형식_에러 = new AuthException(NOT_VALID_PASSWORD);

    @ExplainError
    public GlobalCodeException 비밀번호_확인_에러 =
            new AuthException(NOT_EQUAL_PASSWORD_AND_PASSWORD_CHECK);

    @ExplainError
    public GlobalCodeException 이메일_없음_에러 = new AuthException(NOT_EXIST_VERIFIED_EMAIL);

    @ExplainError
    public GlobalCodeException 이미_존재하는_이메일_에러 = new AuthException(ALREADY_SIGN_UP_EMAIL);
}
