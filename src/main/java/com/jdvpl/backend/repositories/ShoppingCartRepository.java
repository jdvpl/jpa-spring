package com.jdvpl.backend.repositories;

import com.jdvpl.backend.repositories.entity.ShoppingCartEntity;
import com.jdvpl.backend.repositories.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {
    ShoppingCartEntity findByUser(UserEntity user);
}
