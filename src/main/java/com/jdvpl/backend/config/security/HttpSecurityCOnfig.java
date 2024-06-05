package com.jdvpl.backend.config.security;

import com.jdvpl.backend.services.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.jdvpl.backend.config.security.filter.JwtAuthenticationFilter;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

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
                    authConfig.anyRequest().authenticated(); // Requiere autenticación para cualquier otra solicitud
                });
        return  http.build();
    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> builderRequestMatchers() {
        return authConfig -> {
            authConfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/auth/public").permitAll();
            authConfig.requestMatchers("/error").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/products").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());
            authConfig.anyRequest().denyAll();
        };
    }
}
