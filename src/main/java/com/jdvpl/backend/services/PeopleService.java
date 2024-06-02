package com.jdvpl.backend.services;

import com.jdvpl.backend.repositories.PeopleRepository;
import com.jdvpl.backend.repositories.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {
    @Autowired
    PeopleRepository peopleRepository;

    public List<PersonEntity> findAll(){
        return (List<PersonEntity>) peopleRepository.findAll();
    }
    public PersonEntity save(PersonEntity personEntity){
        return  peopleRepository.save(personEntity);
    }
    public String delete(int id){
        if(peopleRepository.findById(id).isEmpty()){
            return "No existe registro con este id";
        }
        peopleRepository.findById(id);
        return  "Registro agregado";
    }
}
