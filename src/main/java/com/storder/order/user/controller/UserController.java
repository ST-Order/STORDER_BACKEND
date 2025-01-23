package com.storder.order.user.controller;

import com.storder.order.global.dto.ApiResponse;
import com.storder.order.user.dto.user.OrderDetailResponseDto;
import com.storder.order.user.dto.user.OrderResponseDto;
import com.storder.order.user.dto.user.ReviewRequestDto;
import com.storder.order.user.dto.user.UserInfoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "MyPage", description = "마이페이지 관련 API")
public class UserController {

    @Operation(
            summary = "사용자 이름과 6개월 구매 통계 조회",
            description = "사용자의 이름과 해당 사용자 6개월간 구매 금액 및 횟수를 조회합니다.")
    @GetMapping("/mypage")
    public UserInfoResponseDto getMyPageSummary() {
        return UserInfoResponseDto.builder()
                .name("가다니")
                .totalOrderPrice(19000)
                .totalOrderCount(3)
                .build();
    }

    @Operation(summary = "주문 내역 조회", description = "주문 내역을 조회합니다.")
    @GetMapping("/mypage/orders/list")
    public OrderResponseDto getOrderList(
            @RequestParam @Parameter(description = "조회할 월", example = "2024-10") String month) {
        List<OrderResponseDto.OrderDto> orders =
                List.of(
                        OrderResponseDto.OrderDto.builder()
                                .menuImage("https://example.com/images/food1.jpg")
                                .orderTime(LocalDateTime.of(2024, 10, 4, 18, 3, 0, 0))
                                .orderStatus("COOKING")
                                .menuName("경성카츠")
                                .price(9000)
                                .build(),
                        OrderResponseDto.OrderDto.builder()
                                .menuImage("https://example.com/images/food2.jpg")
                                .orderTime(LocalDateTime.of(2024, 10, 3, 18, 15, 0, 0))
                                .orderStatus("READY")
                                .menuName("바비든든")
                                .price(10000)
                                .build(),
                        OrderResponseDto.OrderDto.builder()
                                .menuImage("https://example.com/images/food3.jpg")
                                .orderTime(LocalDateTime.of(2024, 10, 1, 13, 0, 0, 0))
                                .orderStatus("READY")
                                .menuName("경성카츠")
                                .price(9000)
                                .build());

        return OrderResponseDto.builder().orders(orders).build();
    }

    @Operation(summary = "주문 상세 조회", description = "특정 주문에 대한 상세 정보를 조회합니다.")
    @GetMapping("/mypage/orders/{orderId}/details")
    public OrderDetailResponseDto getOrderDetails(
            @PathVariable @Parameter(description = "주문 번호", example = "443") int orderId) {
        // 더미 데이터 예시
        List<OrderDetailResponseDto.MenuDto> menu =
                List.of(
                        OrderDetailResponseDto.MenuDto.builder()
                                .menuImage("https://example.com/images/menu1.jpg")
                                .menuName("경양식 돈까스")
                                .option(
                                        List.of(
                                                OrderDetailResponseDto.MenuDto.OptionDto.builder()
                                                        .optionName("밥 추가")
                                                        .build()))
                                .build(),
                        OrderDetailResponseDto.MenuDto.builder()
                                .menuImage("https://example.com/images/menu2.jpg")
                                .menuName("경양식 돈까스")
                                .option(
                                        List.of(
                                                OrderDetailResponseDto.MenuDto.OptionDto.builder()
                                                        .optionName("밥 추가")
                                                        .build()))
                                .build());

        return OrderDetailResponseDto.builder()
                .orderId(orderId)
                .orderTime(LocalDateTime.of(2024, 10, 4, 18, 3, 0, 0))
                .storeName("경성카츠")
                .menu(menu)
                .totalPrice(18000)
                .paymentMethod("KAKAOPAY")
                .paymentTime(LocalDateTime.of(2024, 10, 4, 18, 2, 30, 0))
                .build();
    }

    @Operation(summary = "리뷰 작성", description = "사용자가 메뉴에 대한 리뷰를 작성합니다.")
    @PostMapping("/reviews")
    public ApiResponse createReview(@RequestBody ReviewRequestDto reviewRequestDto) {

        return null;
    }
}
