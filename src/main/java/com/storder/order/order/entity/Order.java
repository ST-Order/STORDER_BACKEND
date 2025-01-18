package com.storder.order.order.entity;

import com.storder.order.global.entity.BaseEntity;
import com.storder.order.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "total_price", nullable = false)
	private Integer totalPrice;

	@Column(nullable = true)
	private Integer discount;

	@Column(name = "final_payment", nullable = false)
	private Integer finalPayment;

	@Enumerated(EnumType.STRING)
	@Column(name = "order_status", nullable = false, length = 10)
	private OrderStatus orderStatus;

	@Column(name = "payed_at")
	private LocalDateTime payedAt;

	@Column(name = "end_at")
	private LocalDateTime endAt;
}