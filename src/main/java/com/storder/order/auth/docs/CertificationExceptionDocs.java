package com.storder.order.auth.docs;

import static com.storder.order.auth.exception.AuthErrorCode.*;

import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class CertificationExceptionDocs implements SwaggerExampleExceptions {

	@ExplainError
	public GlobalCodeException 인증_이메일_전송_에러 = new AuthException(SEND_CERTIFICATION_CODE_ERROR);

}
