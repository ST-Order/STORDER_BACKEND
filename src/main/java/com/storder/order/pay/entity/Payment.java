package com.storder.order.pay.entity;

import com.storder.order.global.entity.BaseEntity;
import com.storder.order.order.entity.Order;
import com.storder.order.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "tid", nullable = false, length = 50)
    private String tid; // 카카오페이 결제 고유 번호

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    private PaymentStatus paymentStatus;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    public void markApproved(LocalDateTime approvedAt) {
        this.paymentStatus = PaymentStatus.APPROVED;
        this.approvedAt = approvedAt;
    }

    public void markCanceled(LocalDateTime canceledAt) {
        this.paymentStatus = PaymentStatus.CANCELED;
        this.canceledAt = canceledAt;
    }
}
