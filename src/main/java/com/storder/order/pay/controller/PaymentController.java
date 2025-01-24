package com.storder.order.pay.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.pay.dto.user.PaymentRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payments Controller", description = "[유저] 결제 관련 API")
public class PaymentController {

	@Operation(summary = "결제 처리", description = "결제 요청 데이터를 받아 결제를 처리합니다.")
	@PostMapping
	public ApiResponse<Void> processPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
		String itemName = paymentRequestDto.getItemName();
		Integer quantity = paymentRequestDto.getQuantity();
		Integer totalAmount = paymentRequestDto.getTotalAmount();
		Integer taxFreeAmount = paymentRequestDto.getTaxFreeAmount();

		String successMessage = String.format("'%s' 결제가 성공적으로 완료되었습니다. (수량: %d, 총 금액: %d원)",
			itemName, quantity, totalAmount);

		return ApiResponse.success(successMessage, null);
	}
}