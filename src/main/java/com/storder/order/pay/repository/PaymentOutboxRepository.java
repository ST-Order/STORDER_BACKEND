package com.storder.order.pay.repository;

import com.storder.order.pay.entity.PaymentOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOutboxRepository extends JpaRepository<PaymentOutbox, Long> {}
