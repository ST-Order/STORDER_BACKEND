package com.storder.order.user.controller;

import com.storder.order.global.annotation.ApiErrorExceptionsExample;
import com.storder.order.global.dto.ApiResponse;
import com.storder.order.menu.service.ReviewService;
import com.storder.order.user.docs.GetUserPageExceptionDocs;
import com.storder.order.user.docs.PostUserReviewExceptionDocs;
import com.storder.order.user.dto.user.OrderDetailResponseDto;
import com.storder.order.user.dto.user.OrderResponseDto;
import com.storder.order.user.dto.user.ReviewRequestDto;
import com.storder.order.user.dto.user.UserInfoResponseDto;
import com.storder.order.user.entity.UserDetails;
import com.storder.order.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "UserPage Controller", description = "[유저] 사용자 마이페이지 관련 API")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;

    @Operation(
            summary = "사용자 이름과 6개월 구매 통계 조회",
            description = "사용자의 이름과 해당 사용자 6개월간 구매 금액 및 횟수를 조회합니다.")
    @ApiErrorExceptionsExample(GetUserPageExceptionDocs.class)
    @GetMapping("/mypage")
    public ResponseEntity<ApiResponse<UserInfoResponseDto>> getMyPageSummary(
            @AuthenticationPrincipal UserDetails userDetails) {
        UserInfoResponseDto userInfo =
                userService.getUserTotalOrderAmount(userDetails.getUser().getUserId());
        return ResponseEntity.ok(ApiResponse.success("사용자 정보 조회에 성공하였습니다.", userInfo));
    }

    @Operation(summary = "주문 내역 조회", description = "주문 내역을 조회합니다.")
    @ApiErrorExceptionsExample(GetUserPageExceptionDocs.class)
    @GetMapping("/mypage/orders/list")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrderList(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam @Parameter(description = "조회할 월", example = "2024-10") String month) {

        OrderResponseDto orderList =
                userService.getUserOrderDataList(userDetails.getUser().getUserId());
        return ResponseEntity.ok(ApiResponse.success("주문 내역 조회에 성공하였습니다.", orderList));
    }

    @Operation(summary = "주문 상세 조회", description = "특정 주문에 대한 상세 정보를 조회합니다.")
    @ApiErrorExceptionsExample(GetUserPageExceptionDocs.class)
    @GetMapping("/mypage/orders/{orderId}/details")
    public ResponseEntity<ApiResponse<OrderDetailResponseDto>> getOrderDetails(
            @PathVariable @Parameter(description = "주문 번호", example = "443") Long orderId) {

        OrderDetailResponseDto orderDetail = userService.getOrderDetails(orderId);

        return ResponseEntity.ok(ApiResponse.success("주문 상세 조회에 성공하였습니다.", orderDetail));
    }

    @Operation(summary = "리뷰 작성", description = "사용자가 메뉴에 대한 리뷰를 작성합니다.")
    @ApiErrorExceptionsExample(PostUserReviewExceptionDocs.class)
    @PostMapping("/reviews")
    public ResponseEntity<ApiResponse<Void>> createReview(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ReviewRequestDto reviewRequestDto) {

        reviewService.createReview(userDetails.getUser().getUserId(), reviewRequestDto);
        return ResponseEntity.ok(ApiResponse.success("리뷰 작성에 성공하였습니다.", null));
    }
}
