package com.storder.order.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storder.order.menu.entity.Menu;
import com.storder.order.menu.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByMenu(Menu menu);
}