package com.storder.order.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storder.order.order.entity.OrderMenu;
import com.storder.order.order.entity.OrderMenuOption;

public interface OrderMenuOptionRepository extends JpaRepository<OrderMenuOption, Long> {
	List<OrderMenuOption> findByOrderMenu(OrderMenu orderMenu);
}