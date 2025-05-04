package com.storder.order.pay.repository;

import com.storder.order.order.entity.Order;
import com.storder.order.pay.entity.Payment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrder(Order order);
}
