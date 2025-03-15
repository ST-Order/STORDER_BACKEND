package com.storder.order.order.repository;

import com.storder.order.order.entity.OrderMenu;
import com.storder.order.order.entity.OrderMenuOption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuOptionRepository extends JpaRepository<OrderMenuOption, Long> {
    List<OrderMenuOption> findByOrderMenu(OrderMenu orderMenu);
}
