package com.jdvpl.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jdvpl.backend.repositories.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String username);
}
