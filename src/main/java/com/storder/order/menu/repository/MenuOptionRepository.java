package com.storder.order.menu.repository;

import com.storder.order.menu.entity.Menu;
import com.storder.order.menu.entity.MenuOption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
    List<MenuOption> findByMenu(Menu menu);
}
