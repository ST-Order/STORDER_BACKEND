package com.storder.order.user.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponseDto {

    @Schema(description = "사용자 이름", example = "가나디")
    private String name;

    @Schema(description = "6개월 간 총 구매 금액", example = "19000")
    private int totalOrderPrice;

    @Schema(description = "6개월 간 총 구매 횟수", example = "3")
    private int totalOrderCount;
}
