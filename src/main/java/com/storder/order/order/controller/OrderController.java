package com.storder.order.order.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.order.dto.user.ActiveOrderResponse;
import com.storder.order.order.dto.user.OrderCreateResponse;
import com.storder.order.order.dto.user.OrderCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Order Controller", description = "[유저] 주문 관련 API")
public class OrderController {

    @PostMapping("/create")
    @Operation(summary = "주문 생성", description = "장바구니 정보를 받아와 주문을 생성합니다.")
    public ResponseEntity<ApiResponse<OrderCreateResponse>> createOrder(
            @RequestBody OrderCreateRequest request) {

        // OrderCreateResponse order = null;
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/active")
    @Operation(summary = "활성화된 주문 현황 조회", description = "활성화된 주문 현황을 조회합니다.")
    public ResponseEntity<ApiResponse<List<ActiveOrderResponse>>> getActiveOrders() {

        // List<OrderCreateResponse> activeOrders = null;
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
