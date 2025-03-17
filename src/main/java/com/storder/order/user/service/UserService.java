package com.storder.order.user.service;

import static com.storder.order.order.exception.OrderErrorCode.*;

import com.storder.order.order.dto.user.OrderSummaryDto;
import com.storder.order.order.entity.Order;
import com.storder.order.order.entity.OrderMenu;
import com.storder.order.order.repository.OrderMenuOptionRepository;
import com.storder.order.order.repository.OrderMenuRepository;
import com.storder.order.order.repository.OrderRepository;
import com.storder.order.order.service.OrderService;
import com.storder.order.user.dto.user.OrderDetailResponseDto;
import com.storder.order.user.dto.user.OrderResponseDto;
import com.storder.order.user.dto.user.UserInfoResponseDto;
import com.storder.order.user.entity.User;
import com.storder.order.user.exception.UserErrorCode;
import com.storder.order.user.exception.UserException;
import com.storder.order.user.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final OrderMenuOptionRepository orderMenuOptionRepository;
    // TODO: 추후 추가 로직 (에러 처리) 구현 필요
    public UserInfoResponseDto getUserTotalOrderAmount(Long userId) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserInfoResponseDto.builder()
                .name(user.getName())
                .totalOrderPrice(orderService.getTotalOrderAmountForPeriod(userId, 6))
                .totalOrderCount(user.getOrderCount())
                .build();
    }


    @Transactional(readOnly = true)
    public OrderResponseDto getUserOrderDataList(Long userId) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);

        Integer totalOrderAmount =
                orderRepository.findTotalOrderAmountByUserAndPeriod(
                        userId, startDate.atStartOfDay(), endDate.atStartOfDay());
        Integer totalOrderCount =
                orderRepository.findTotalOrderCountByUserAndPeriod(
                        userId, startDate.atStartOfDay(), endDate.atStartOfDay());

        List<OrderSummaryDto> orderSummaries =
                orderRepository.findUserOrdersWithinPeriod(userId, startDate, endDate);

        // 주문 ID 기준으로 그룹화
        Map<Long, List<OrderResponseDto.StoreOrderDto>> groupedByOrder =
                orderSummaries.stream()
                        .collect(
                                Collectors.groupingBy(
                                        OrderSummaryDto::getOrderId,
                                        Collectors.mapping(
                                                summary ->
                                                        OrderResponseDto.StoreOrderDto.builder()
                                                                .storeImage(summary.getStoreImage())
                                                                .storeName(summary.getStoreName())
                                                                .totalOrderPrice(
                                                                        summary
                                                                                .getTotalOrderPrice())
                                                                .endAt(summary.getEndAt())
                                                                .build(),
                                                Collectors.toList())));

        List<OrderResponseDto.OrderGroupDto> orderGroups =
                groupedByOrder.entrySet().stream()
                        .map(
                                entry -> {
                                    Long orderId = entry.getKey();
                                    List<OrderResponseDto.StoreOrderDto> storeOrders =
                                            entry.getValue();
                                    LocalDate orderDate =
                                            orderSummaries.stream()
                                                    .filter(
                                                            summary ->
                                                                    summary.getOrderId()
                                                                            .equals(orderId))
                                                    .findFirst()
                                                    .map(OrderSummaryDto::getOrderDate)
                                                    .orElse(null);

                                    return OrderResponseDto.OrderGroupDto.builder()
                                            .orderId(orderId)
                                            .orderDate(orderDate)
                                            .storeOrders(storeOrders)
                                            .build();
                                })
                        .collect(Collectors.toList());

        return OrderResponseDto.builder()
                .totalOrderCount(totalOrderCount)
                .totalPrice(totalOrderAmount)
                .orders(orderGroups)
                .build();
    }

    @Transactional(readOnly = true)
    public OrderDetailResponseDto getOrderDetails(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow();

        List<OrderMenu> orderMenus = orderMenuRepository.findByOrder(order);

        List<OrderDetailResponseDto.MenuDto> menuDtos =
                orderMenus.stream()
                        .map(
                                orderMenu ->
                                        OrderDetailResponseDto.MenuDto.builder()
                                                .menuImage(orderMenu.getMenu().getMenuImage())
                                                .menuName(orderMenu.getMenu().getMenuName())
                                                .option(
                                                        orderMenuOptionRepository
                                                                .findByOrderMenu(orderMenu)
                                                                .stream()
                                                                .map(
                                                                        orderOption ->
                                                                                OrderDetailResponseDto
                                                                                        .MenuDto
                                                                                        .OptionDto
                                                                                        .builder()
                                                                                        .optionName(
                                                                                                orderOption
                                                                                                        .getMenuOption()
                                                                                                        .getOptionName())
                                                                                        .build())
                                                                .collect(Collectors.toList()))
                                                .build())
                        .collect(Collectors.toList());

        return OrderDetailResponseDto.builder()
                .orderId(order.getOrderId())
                .orderTime(order.getCreatedAt())
                .storeName(orderMenus.get(0).getMenu().getStore().getStoreName())
                .menu(menuDtos)
                .totalPrice(order.getTotalPrice())
                // :TODO 결제 관련 필드는 나중에 구현
                // .paymentMethod(order.getPaymentMethod())
                // .paymentTime(order.getPayedAt())
                .build();
    }
}
