package com.jdvpl.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdvpl.backend.controller.dto.UserDto;
import com.jdvpl.backend.controller.dto.UserMapper;
import com.jdvpl.backend.repositories.UserRepository;
import com.jdvpl.backend.repositories.entity.UserEntity;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

    public List<UserDto> findAll(){
        List<UserEntity> users=userRepository.findAll();
        List<UserDto> userDtos=users.stream().map(user ->   
        UserMapper.mapper.userEntityToUserDto(user)).collect(Collectors.toList());
        return  userDtos;
    }
    public UserEntity save(UserEntity personEntity){
        return  userRepository.save(personEntity);
    }
    public String delete(Long id){
        if(userRepository.findById(id).isEmpty()){
            return "No existe registro con este id";
        }
        userRepository.deleteById(id);
        return  "Registro eliminado";
    }
}
