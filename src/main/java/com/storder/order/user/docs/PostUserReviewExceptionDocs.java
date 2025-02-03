package com.storder.order.user.docs;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.auth.exception.AuthException;
import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.menu.exception.MenuErrorCode;

@ExceptionDoc
public class PostUserReviewExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 액세스_토큰_없음 = new AuthException(AuthErrorCode.ACCESS_TOKEN_NOT_EXIST);

    @ExplainError
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError
    public GlobalCodeException 리뷰_작성_유효하지_않은_데이터 =
            new GlobalCodeException(MenuErrorCode.INVALID_REVIEW_DATA);

    @ExplainError
    public GlobalCodeException 주문_내역_없는_유저_리뷰_작성 =
            new GlobalCodeException(MenuErrorCode.REVIEW_WITHOUT_ORDER);

    @ExplainError
    public GlobalCodeException 본인_주문_아닌_리뷰 =
            new GlobalCodeException(MenuErrorCode.REVIEW_NOT_FROM_USER_ORDER);

    @ExplainError
    public GlobalCodeException 이미_작성된_리뷰 = new GlobalCodeException(MenuErrorCode.DUPLICATE_REVIEW);
}
