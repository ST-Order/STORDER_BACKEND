package com.storder.order.order.service;

import com.storder.order.order.repository.OrderRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Integer getTotalOrderAmountForLastSixMonths(Long userId) {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        LocalDateTime now = LocalDateTime.now();
        return orderRepository.findTotalOrderAmountByUserAndPeriod(userId, sixMonthsAgo, now);
    }
}
