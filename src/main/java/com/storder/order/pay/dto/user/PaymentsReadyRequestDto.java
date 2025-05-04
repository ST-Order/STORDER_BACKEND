package com.storder.order.pay.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "페이 결제 준 최종 요청 DTO")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentsReadyRequestDto {

    @Schema(description = "주문 ID", example = "1")
    private Long orderId;

    @Schema(description = "상품명", example = "제육덮밥")
    private String itemName;

    @Schema(description = "상품 수량", example = "1")
    private Integer quantity;

    @Schema(description = "총 결제 금액", example = "2200")
    private Integer totalAmount;

    @Schema(description = "비과세 금액", example = "0")
    private Integer taxFreeAmount;
}
