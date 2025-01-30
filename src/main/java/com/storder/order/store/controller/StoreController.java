package com.storder.order.store.controller;

import com.storder.order.global.annotation.ApiErrorExceptionsExample;
import com.storder.order.global.dto.ApiResponse;
import com.storder.order.store.docs.StoreHomeExceptionDocs;
import com.storder.order.store.docs.StoreOpenExceptionDocs;
import com.storder.order.store.dto.user.StoreHomeResponse;
import com.storder.order.store.dto.user.StoreOpenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stores")
@Tag(name = "Store Controller", description = "[유저] 상점 관련 API")
public class StoreController {

    @GetMapping("/home")
    @Operation(summary = "상점 홈 조회", description = "상점 홈 화면을 조회합니다.")
    @ApiErrorExceptionsExample(StoreHomeExceptionDocs.class)
    public ResponseEntity<ApiResponse<StoreHomeResponse>> getStoreHome() {

        // StoreHomeResponse storeHome = null;
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/open-status")
    @Operation(summary = "상점 오픈 여부 조회", description = "상점들의 오픈 여부를 조회합니다.")
    @ApiErrorExceptionsExample(StoreOpenExceptionDocs.class)
    public ResponseEntity<ApiResponse<List<StoreOpenResponse>>> getStoreOpenStatus() {

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
