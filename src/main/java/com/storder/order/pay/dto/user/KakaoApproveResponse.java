package com.storder.order.pay.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "카카오페이 결제 승인 응답 DTO")
public class KakaoApproveResponse {

    @Schema(description = "요청 고유 번호 (AID)", example = "A5678901234567890123")
    private String aid;

    @Schema(description = "결제 고유 번호 (TID)", example = "T1234567890123456789")
    private String tid;

    @Schema(description = "가맹점 코드", example = "TC0ONETIME")
    private String cid;

    @JsonProperty("partner_order_id")
    @Schema(description = "가맹점 주문 번호", example = "order-123")
    private String partnerOrderId;

    @JsonProperty("partner_user_id")
    @Schema(description = "가맹점 사용자 ID", example = "user-456")
    private String partnerUserId;

    @JsonProperty("payment_method_type")
    @Schema(description = "결제 수단", example = "CARD")
    private String paymentMethodType;

    @JsonProperty("item_name")
    @Schema(description = "상품명", example = "제육덮밥")
    private String itemName;

    @Schema(description = "상품 수량", example = "1")
    private Integer quantity;

    @JsonProperty("approved_at")
    @Schema(description = "결제 승인 시각", example = "2023-07-15T21:18:22")
    private String approvedAt;
}
