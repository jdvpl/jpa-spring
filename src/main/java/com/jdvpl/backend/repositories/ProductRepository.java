package com.jdvpl.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdvpl.backend.repositories.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    //public List<ProductEntity> findAllByCategory(CategoryEntity category);
}
