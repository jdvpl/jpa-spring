package com.jdvpl.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdvpl.backend.controller.dto.AuthenticationRequestDto;
import com.jdvpl.backend.controller.dto.AuthenticationResponse;
import com.jdvpl.backend.services.AuthenticationService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequestDto authentication){
        
        AuthenticationResponse token=authenticationService.login(authentication);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/public")
    public String getMethodName() {
        return "Public method";
    }
    

    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") Long id){
        return authenticationService.delete(id);
    }
}
