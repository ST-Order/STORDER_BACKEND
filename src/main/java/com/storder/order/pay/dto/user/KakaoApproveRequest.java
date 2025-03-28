package com.storder.order.pay.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카카오페이 결제 승인 요청 DTO")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KakaoApproveRequest {

    @Schema(description = "가맹점 코드", example = "TC0ONETIME")
    private String cid;

    @Schema(description = "결제 고유 번호 (TID)", example = "T1234567890123456789")
    private String tid;

    @JsonProperty("partner_order_id")
    @Schema(description = "가맹점 주문 번호", example = "order-123")
    private String partnerOrderId;

    @JsonProperty("partner_user_id")
    @Schema(description = "가맹점 사용자 ID", example = "user-456")
    private String partnerUserId;

    @JsonProperty("pg_token")
    @Schema(description = "결제 승인 인증 토큰", example = "pg_token_example")
    private String pgToken;
}
