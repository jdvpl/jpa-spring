package com.jdvpl.backend.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.jdvpl.backend.config.security.filter.JwtAuthenticationFilter;
import com.jdvpl.backend.utils.Permission;

@Component
@EnableWebSecurity
class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrfConfig-> csrfConfig.disable())
            .sessionManagement(sessionManagementConfig->sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authConfig->{
                authConfig.requestMatchers(HttpMethod.POST,"/auth/login").permitAll();
                authConfig.requestMatchers(HttpMethod.GET,"/auth/public").permitAll();
                authConfig.requestMatchers("/error").permitAll();
                authConfig.requestMatchers(HttpMethod.GET,"/products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                authConfig.requestMatchers(HttpMethod.POST,"/products").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());
                authConfig.anyRequest().denyAll();
            });     
        return  http.build();
    }
}
