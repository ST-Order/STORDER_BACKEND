package com.storder.order.notice.docs;

import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.notice.exception.NoticeErrorCode;
import com.storder.order.notice.exception.NoticeException;
import com.storder.order.store.exception.StoreErrorCode;
import com.storder.order.store.exception.StoreException;

public class NoticeDetailsExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 잘못된_공지사항_카테고리 = new NoticeException(NoticeErrorCode.INVALID_NOTICE_CATEGORY);

    @ExplainError
    public GlobalCodeException 잘못된_공지사항_노출기간 = new NoticeException(NoticeErrorCode.INVALID_NOTICE_DISPLAY_TIME);

    @ExplainError
    public GlobalCodeException 공지사항_존재하지_않음 = new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 식당_존재하지_않음 = new StoreException(StoreErrorCode.STORE_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 작성날짜가_미래인_공지사항 = new NoticeException(NoticeErrorCode.NOTICE_FUTURE_DATE);

    @ExplainError
    public GlobalCodeException 노출기간이_만료된_공지사항 = new NoticeException(NoticeErrorCode.NOTICE_EXPIRED);
}
