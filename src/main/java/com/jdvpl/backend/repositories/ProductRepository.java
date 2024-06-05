package com.jdvpl.backend.repositories;

import com.jdvpl.backend.repositories.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jdvpl.backend.repositories.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    //public List<ProductEntity> findAllByCategory(CategoryEntity category);
}
