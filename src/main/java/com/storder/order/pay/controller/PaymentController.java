package com.storder.order.pay.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.pay.dto.user.KakaoApproveResponse;
import com.storder.order.pay.dto.user.KakaoReadyResponse;
import com.storder.order.pay.dto.user.PaymentRequestDto;
import com.storder.order.pay.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payments Controller", description = "[유저] 결제 관련 API")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "카카오페이 결제 준비", description = "카카오페이 결제를 위한 ready 요청을 처리합니다.")
    @PostMapping("/ready")
    public ResponseEntity<ApiResponse<KakaoReadyResponse>> requestPayment(
            @RequestBody PaymentRequestDto requestDto) {

        KakaoReadyResponse response = paymentService.requestPayment(requestDto);
        return ResponseEntity.ok(ApiResponse.success("카카오페이 결제 준비 완료", response));
    }

    @Operation(summary = "카카오페이 결제 승인", description = "pg_token을 받아 결제를 승인합니다.")
    @GetMapping("/approve")
    public ResponseEntity<ApiResponse<KakaoApproveResponse>> approvePayment(
            @RequestParam("pg_token") String pgToken, @RequestParam("orderId") Long orderId) {

        KakaoApproveResponse response = paymentService.approvePayment(pgToken, orderId);
        return ResponseEntity.ok(ApiResponse.success("카카오페이 결제 승인 완료", response));
    }
}
