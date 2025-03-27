package com.storder.order.pay.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카카오페이 결제 요청 DTO")
public class PaymentRequestDto {

    @Schema(description = "가맹점 주문 번호", example = "order-123")
    private String partnerOrderId;

    @Schema(description = "가맹점 사용자 ID", example = "user-456")
    private String partnerUserId;

    @Schema(description = "상품명", example = "제육덮밥")
    private String itemName;

    @Schema(description = "상품 수량", example = "1")
    private Integer quantity;

    @Schema(description = "총 결제 금액", example = "5000")
    private Integer totalAmount;

    @Schema(description = "비과세 금액", example = "0")
    private Integer taxFreeAmount;

    @Schema(description = "부가세 금액", example = "500")
    private Integer vatAmount;

    @Schema(description = "결제 성공 리다이렉트 URL", example = "https://yourapp.com/payment/success")
    private String approvalUrl;

    @Schema(description = "결제 취소 리다이렉트 URL", example = "https://yourapp.com/payment/cancel")
    private String cancelUrl;

    @Schema(description = "결제 실패 리다이렉트 URL", example = "https://yourapp.com/payment/fail")
    private String failUrl;
}
