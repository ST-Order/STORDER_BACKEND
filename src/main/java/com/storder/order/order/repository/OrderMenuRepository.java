package com.storder.order.order.repository;

import com.storder.order.order.entity.Order;
import com.storder.order.order.entity.OrderMenu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
    List<OrderMenu> findByOrder(Order order);
}
