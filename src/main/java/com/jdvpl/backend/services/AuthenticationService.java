package com.jdvpl.backend.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdvpl.backend.controller.dto.AuthenticationRequestDto;
import com.jdvpl.backend.controller.dto.AuthenticationResponse;
import com.jdvpl.backend.controller.dto.RegisterRequestDto;
import com.jdvpl.backend.errors.ValidationHandler;
import com.jdvpl.backend.repositories.UserRepository;
import com.jdvpl.backend.repositories.entity.UserEntity;
import com.jdvpl.backend.utils.Role;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
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
    public AuthenticationResponse register( RegisterRequestDto authentication) {
       
        String password= passwordEncoder.encode(authentication.getPassword());
        Optional<UserEntity> exists = userRepository.findByUsername(authentication.getUsername());
        if(exists.isPresent()){
            throw new  ValidationHandler.CustomerError("El usuario ya existe");
        }   
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(authentication.getUsername());
        newUserEntity.setPassword(password);
        newUserEntity.setName(authentication.getName());
        newUserEntity.setLastName(authentication.getLastName());
        newUserEntity.setRole(Role.CUSTOMER);
        UserEntity user = userRepository.save(newUserEntity);
        String jwt=jwtService.generateToken(user,generateExtraClaims(user));
        AuthenticationResponse token= new AuthenticationResponse(jwt);
        return  token;

    }

    private Map<String,Object> generateExtraClaims(UserEntity user) {
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("lastName",user.getLastName());
        extraClaims.put("role", "ROLE_"+user.getRole().name());
        extraClaims.put("permissions",user.getAuthorities());
        return  extraClaims;
    }
}
