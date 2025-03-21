package com.storder.order.order.service;

import com.storder.order.order.repository.OrderRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public int getTotalOrderAmountForPeriod(Long userId, int months) {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(months);
        LocalDateTime now = LocalDateTime.now();
        return orderRepository.findTotalOrderAmountByUserAndPeriod(userId, startDate, now);
    }

    public int getTotalOrderCountForPeriod(Long userId, int months) {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(months);
        LocalDateTime now = LocalDateTime.now();
        return orderRepository.findTotalOrderCountByUserAndPeriod(userId, startDate, now);
    }
}
