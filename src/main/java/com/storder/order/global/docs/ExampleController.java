package com.storder.order.global.docs;

import com.storder.order.auth.exception.AuthErrorCode;
import com.storder.order.global.annotation.ApiErrorCodeExample;
import com.storder.order.global.exception.GlobalErrorCode;
import com.storder.order.menu.exception.MenuErrorCode;
import com.storder.order.notice.exception.NoticeErrorCode;
import com.storder.order.order.exception.OrderErrorCode;
import com.storder.order.pay.exception.PayErrorCode;
import com.storder.order.store.exception.StoreErrorCode;
import com.storder.order.user.exception.UserErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/example")
@RequiredArgsConstructor
@Tag(name = "Exception Document", description = "예제 에러코드 문서화")
public class ExampleController {
    @GetMapping("/global")
    @ApiErrorCodeExample(GlobalErrorCode.class)
    @Operation(summary = "글로벌 (aop, 서버 내부 오류등)  관련 에러 코드 나열")
    public void example() {}

    @GetMapping("/user")
    @ApiErrorCodeExample(UserErrorCode.class)
    @Operation(summary = "유저 관련 에러 코드 나열")
    public void example2() {}

    @GetMapping("/auth")
    @ApiErrorCodeExample(AuthErrorCode.class)
    @Operation(summary = "인증 관련 에러 코드 나열")
    public void example3() {}

    @GetMapping("/store")
    @ApiErrorCodeExample(StoreErrorCode.class)
    @Operation(summary = "식당(상점) 관련 에러 코드 나열")
    public void example4() {}

    @GetMapping("/order")
    @ApiErrorCodeExample(OrderErrorCode.class)
    @Operation(summary = "주문 관련 에러 코드 나열")
    public void example5() {}

    @GetMapping("/pay")
    @ApiErrorCodeExample(PayErrorCode.class)
    @Operation(summary = "결제 관련 에러 코드 나열")
    public void example6() {}

    @GetMapping("/menu")
    @ApiErrorCodeExample(MenuErrorCode.class)
    @Operation(summary = "메뉴 관련 에러 코드 나열")
    public void example7() {}

    @GetMapping("/notice")
    @ApiErrorCodeExample(NoticeErrorCode.class)
    @Operation(summary = "공지사항 관련 에러 코드 나열")
    public void example8() {}
}
