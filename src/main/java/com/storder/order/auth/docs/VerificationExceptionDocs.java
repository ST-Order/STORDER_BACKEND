package com.storder.order.auth.docs;

import static com.storder.order.auth.exception.AuthErrorCode.*;

import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class VerificationExceptionDocs implements SwaggerExampleExceptions {

	@ExplainError
	public GlobalCodeException 인증번호_불일치 = new AuthException(VERIFY_CERTIFICATION_CODE_ERROR);

}
