package com.jdvpl.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdvpl.backend.controller.dto.UserDto;
import com.jdvpl.backend.repositories.entity.UserEntity;
import com.jdvpl.backend.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody UserDto personEntity){
        return authenticationService.save(personEntity);
    }

    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") Long id){
        return authenticationService.delete(id);
    }
}
