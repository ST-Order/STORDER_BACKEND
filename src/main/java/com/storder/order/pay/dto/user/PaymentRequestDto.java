package com.storder.order.pay.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequestDto {

    @Schema(description = "제품명", example = "제육덮밥")
    private String itemName;

    @Schema(description = "수량", example = "1")
    private Integer quantity;

    @Schema(description = "총 금액", example = "5000")
    private Integer totalAmount;

    @Schema(description = "tax free 금액", example = "0")
    private Integer taxFreeAmount;
}
