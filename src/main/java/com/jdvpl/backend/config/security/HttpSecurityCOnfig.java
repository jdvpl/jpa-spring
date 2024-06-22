package com.jdvpl.backend.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.jdvpl.backend.config.security.filter.JwtAuthenticationFilter;
import com.jdvpl.backend.services.CustomAuthenticationEntryPoint;
import com.jdvpl.backend.utils.Role;

@Component
//@EnableWebSecurity
//@EnableMethodSecurity
class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Value("${api.path.admin}")
    private  String adminPath;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrfConfig-> csrfConfig.disable())
                .sessionManagement(sessionManagementConfig ->
                        sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandlingConfig ->
                        exceptionHandlingConfig.authenticationEntryPoint(customAuthenticationEntryPoint))
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/auth/**").permitAll(); // Permitir todas las rutas bajo /auth/
                    authConfig.requestMatchers("/error").permitAll();
                    authConfig.requestMatchers("/products/"+adminPath+"/**", "/category/"+adminPath+"/**").hasRole(Role.ADMINISTRATOR.name());
                    authConfig.requestMatchers("/webjars/**","/v3/api-docs/**","/swagger-resources/**","/swagger-ui/**").permitAll();
                    authConfig.anyRequest().authenticated(); // Requiere autenticación para cualquier otra solicitud
                });
       
        return  http.build();
    }
}
