package com.storder.order.pay.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카카오페이 결제 요청 DTO")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentRequestDto {

    @Schema(description = "CID", example = "TC0ONETIME")
    private String cid;

    @Schema(description = "가맹점 주문 번호", example = "1")
    private String partnerOrderId;

    @Schema(description = "가맹점 사용자 ID", example = "1")
    private String partnerUserId;

    @Schema(description = "상품명", example = "제육덮밥")
    private String itemName;

    @Schema(description = "상품 수량", example = "1")
    private Integer quantity;

    @Schema(description = "총 결제 금액", example = "2200")
    private Integer totalAmount;

    @Schema(description = "비과세 금액", example = "0")
    private Integer taxFreeAmount;

    @Schema(description = "부가세 금액", example = "100")
    private Integer vatAmount;

    @Schema(description = "결제 성공 리다이렉트 URL", example = "http://localhost:8080")
    private String approvalUrl;

    @Schema(description = "결제 취소 리다이렉트 URL", example = "http://localhost:8080")
    private String cancelUrl;

    @Schema(description = "결제 실패 리다이렉트 URL", example = "http://localhost:8080")
    private String failUrl;
}
