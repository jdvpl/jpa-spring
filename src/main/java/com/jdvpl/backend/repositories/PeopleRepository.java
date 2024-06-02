package com.jdvpl.backend.repositories;

import com.jdvpl.backend.repositories.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<PersonEntity,Integer> {
}
