package com.storder.order.store.dto.seller;

import com.storder.order.store.entity.OpenStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "상점 오픈/닫기 응답 DTO")
@Builder
public class OpenCloseResponse {

    @Schema(description = "상점 ID", example = "1")
    private Long storeId;

    @Schema(description = "상점 이름", example = "바비든든")
    private String storeName;

    @Schema(description = "오픈 상태", example = "OPEN")
    private OpenStatus openStatus;
}
