package com.jdvpl.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jdvpl.backend.repositories.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {
}
