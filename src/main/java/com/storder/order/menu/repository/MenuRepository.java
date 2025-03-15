package com.storder.order.menu.repository;

import com.storder.order.menu.entity.Menu;
import com.storder.order.store.entity.Store;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByStoreOrderByMenuNameAsc(Store store);

    Optional<Menu> findByStoreAndMenuName(Store store, String menuName);
}
