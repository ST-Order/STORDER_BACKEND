package com.storder.order.order.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.order.dto.seller.FinishedOrderListResponse;
import com.storder.order.order.dto.seller.OrderListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/seller/orders")
@Tag(name = "Seller Order Controller", description = "[사장님] 주문 관련 API")
public class SellerOrderController {

    @GetMapping("/{storeId}/pay")
    @Operation(summary = "결제 완료 주문 조회", description = "당일 결제 완료 상태인 주문을 조회합니다.")
    public ResponseEntity<ApiResponse<List<OrderListResponse>>> getPaidOrders(
            @Parameter(description = "상점 ID", example = "1") @PathVariable Long storeId) {

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{storeId}/cooking")
    @Operation(summary = "조리중 주문 조회", description = "당일 조리중 상태인 주문을 조회합니다.")
    public ResponseEntity<ApiResponse<List<OrderListResponse>>> getCookingOrders(
            @Parameter(description = "상점 ID", example = "1") @PathVariable Long storeId) {

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{storeId}/ready")
    @Operation(summary = "준비 완료 주문 조회", description = "당일 준비 완료 상태인 주문을 조회합니다.")
    public ResponseEntity<ApiResponse<List<OrderListResponse>>> getReadyOrders(
            @Parameter(description = "상점 ID", example = "1") @PathVariable Long storeId) {

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping("/{orderId}/status")
    @Operation(summary = "주문 상태 변경", description = "주문의 상태를 PAY -> COOKING -> READY로 변경합니다.")
    public ResponseEntity<ApiResponse<Void>> updateOrderStatus(
            @Parameter(description = "주문 ID", example = "123") @PathVariable Long orderId) {

        return ResponseEntity.ok(ApiResponse.success("주문 상태가 변경되었습니다.", null));
    }

    @GetMapping("/{storeId}/history")
    @Operation(summary = "주문 처리 내역 조회", description = "특정 날짜의 일일 주문 처리 내역을 조회합니다.")
    public ResponseEntity<ApiResponse<FinishedOrderListResponse>> getOrderHistory(
            @Parameter(description = "상점 ID", example = "1") @PathVariable Long storeId,
            @Parameter(description = "조회할 날짜 (YYYY-MM-DD)", example = "2024-12-31") @RequestParam
                    String date) {

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
