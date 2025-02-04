package com.storder.order.menu.docs.user;

import com.storder.order.global.annotation.ExceptionDoc;
import com.storder.order.global.annotation.ExplainError;
import com.storder.order.global.exception.GlobalCodeException;
import com.storder.order.global.interfaces.SwaggerExampleExceptions;
import com.storder.order.menu.exception.MenuErrorCode;
import com.storder.order.menu.exception.MenuException;
import com.storder.order.menu.exception.ReviewErrorCode;
import com.storder.order.menu.exception.ReviewException;
import com.storder.order.store.exception.StoreErrorCode;
import com.storder.order.store.exception.StoreException;

@ExceptionDoc
public class MenuDetailsExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 식당_존재하지_않음 = new StoreException(StoreErrorCode.STORE_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 메뉴_존재하지_않음 = new MenuException(MenuErrorCode.MENU_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 잘못된_별점_수치 = new ReviewException(ReviewErrorCode.INVALID_STAR_RATING);

    @ExplainError
    public GlobalCodeException 별점_계산_실패 = new ReviewException(ReviewErrorCode.STAR_RATING_CONFLICT);
}
