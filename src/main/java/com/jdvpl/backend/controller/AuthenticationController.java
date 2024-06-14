package com.jdvpl.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdvpl.backend.controller.dto.AuthenticationRequestDto;
import com.jdvpl.backend.controller.dto.AuthenticationResponse;
import com.jdvpl.backend.controller.dto.RegisterRequestDto;
import com.jdvpl.backend.services.AuthenticationService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    //@PreAuthorize("permitAll")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequestDto authentication){
        
        AuthenticationResponse token=authenticationService.login(authentication);
        return ResponseEntity.ok(token);
    }

    //@PreAuthorize("permitAll")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequestDto authentication){
        
        AuthenticationResponse token=authenticationService.register(authentication);
        return ResponseEntity.ok(token);
    }
    
}
