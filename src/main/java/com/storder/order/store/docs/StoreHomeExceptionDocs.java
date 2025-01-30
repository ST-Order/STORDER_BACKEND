package com.storder.order.store.docs;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.notice.exception.NoticeErrorCode;
import com.storder.order.notice.exception.NoticeException;
import com.storder.order.store.exception.StoreErrorCode;
import com.storder.order.store.exception.StoreException;

@ExceptionDoc
public class StoreHomeExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 액세스_토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);

    @ExplainError
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError
    public GlobalCodeException 식당_존재하지_않음 = new StoreException(StoreErrorCode.STORE_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 식당_이미지_존재하지_않음 = new StoreException(StoreErrorCode.STORE_IMAGE_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 공지사항_존재하지_않음 = new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND);


}
