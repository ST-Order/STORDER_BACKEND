package com.storder.order.user.service;

import com.storder.order.order.service.OrderService;
import com.storder.order.user.dto.user.UserInfoResponseDto;
import com.storder.order.user.entity.User;
import com.storder.order.user.exception.UserErrorCode;
import com.storder.order.user.exception.UserException;
import com.storder.order.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public UserInfoResponseDto getUserTotalOrderAmount(Long userId) {
		// TODO: 추후 추가 로직 (에러 처리) 구현 필요
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserInfoResponseDto.builder()
                .name(user.getName())
                .totalOrderPrice(orderService.getTotalOrderAmountForLastSixMonths(userId))
                .totalOrderCount(user.getOrderCount())
                .build();
    }
}
