package com.jdvpl.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdvpl.backend.repositories.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
