package com.jdvpl.backend.config.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jdvpl.backend.repositories.UserRepository;
import com.jdvpl.backend.repositories.entity.UserEntity;
import com.jdvpl.backend.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       String authHeader=request.getHeader("authorization");

       if(authHeader==null || !authHeader.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
       }
       String jwt=authHeader.split(" ")[1];
       String username=jwtService.extractUsername(jwt);
    
       UserEntity user=userRepository.findByUsername(username).get();
       UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
        user, null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
    
}
