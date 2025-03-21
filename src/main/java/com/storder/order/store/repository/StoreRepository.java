package com.storder.order.store.repository;

import com.storder.order.store.entity.Store;
import com.storder.order.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByOwner(User owner);
}
