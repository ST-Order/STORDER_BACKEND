package com.storder.order.store.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.store.dto.seller.OpenCloseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/seller/stores")
@Tag(name = "Seller Store Controller", description = "[사장님] 상점 관련 API")
public class SellerStoreController {

    @PatchMapping("/{storeId}/open")
    @Operation(summary = "식당 오픈", description = "식당을 오픈 상태로 전환합니다.(영업시작)")
    public ResponseEntity<ApiResponse<OpenCloseResponse>> storeOpen(
            @Parameter(description = "식당 ID", example = "1") @PathVariable Long storeId) {
        return ResponseEntity.ok(ApiResponse.success("식당을 오픈하였습니다.", null));
    }

    @PatchMapping("/{storeId}/close")
    @Operation(summary = "식당 마감", description = "식당을 영업 종료합니다.")
    public ResponseEntity<ApiResponse<OpenCloseResponse>> storeClose(
            @Parameter(description = "식당 ID", example = "1") @PathVariable Long storeId) {
        return ResponseEntity.ok(ApiResponse.success("식당 영업 종료하였습니다.", null));
    }
}
