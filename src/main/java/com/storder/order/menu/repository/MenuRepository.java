package com.storder.order.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storder.order.menu.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
