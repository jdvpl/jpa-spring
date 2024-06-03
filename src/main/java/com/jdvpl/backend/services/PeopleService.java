package com.jdvpl.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdvpl.backend.repositories.UserRepository;
import com.jdvpl.backend.repositories.entity.UserEntity;

@Service
public class PeopleService {
    @Autowired
    UserRepository userRepository;

    public List<UserEntity> findAll(){
        return (List<UserEntity>) userRepository.findAll();
    }
    public UserEntity save(UserEntity personEntity){
        return  userRepository.save(personEntity);
    }
    public String delete(int id){
        if(userRepository.findById(id).isEmpty()){
            return "No existe registro con este id";
        }
        userRepository.deleteById(id);
        return  "Registro eliminado";
    }
}
