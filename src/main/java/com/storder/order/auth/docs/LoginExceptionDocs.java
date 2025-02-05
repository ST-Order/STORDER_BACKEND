package com.storder.order.auth.docs;

import static com.storder.order.auth.exception.AuthErrorCode.*;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class LoginExceptionDocs implements SwaggerExampleExceptions {

	@ExplainError
	public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

	@ExplainError
	public GlobalCodeException 액세스_토큰이_Bearer로_시작하지_않음 = new AuthException(NOT_START_WITH_BEARER);

	@ExplainError
	public GlobalCodeException 액세스_토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);

	@ExplainError
	public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

	@ExplainError
	public GlobalCodeException 이메일이_존재하지_않음 = new AuthException(NOT_EXIST_LOGIN_EMAIL);

	@ExplainError
	public GlobalCodeException 비밀번호_유효하지_않음 = new AuthException(NOT_EQUAL_PASSWORD);

}
