package com.jdvpl.backend.repositories;

import com.jdvpl.backend.repositories.entity.ShoppingCartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProductEntity, Long> {
}
