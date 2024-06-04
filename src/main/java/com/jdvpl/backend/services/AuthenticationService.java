package com.jdvpl.backend.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.jdvpl.backend.controller.dto.AuthenticationRequestDto;
import com.jdvpl.backend.controller.dto.AuthenticationResponse;
import com.jdvpl.backend.repositories.UserRepository;
import com.jdvpl.backend.repositories.entity.UserEntity;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

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

    public AuthenticationResponse login( AuthenticationRequestDto authentication) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            authentication.getUsername(),
            authentication.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);
        UserEntity user = userRepository.findByUsername(authentication.getUsername()).get();

        String jwt=jwtService.generateToken(user,generateExtraClaims(user));
        AuthenticationResponse token= new AuthenticationResponse(jwt);
        return  token;

    }

    private Map<String,Object> generateExtraClaims(UserEntity user) {
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("lastName",user.getLastName());
        extraClaims.put("role",user.getRole().name());
        return  extraClaims;
    }
}
