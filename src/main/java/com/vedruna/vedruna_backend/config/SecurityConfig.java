package com.vedruna.vedruna_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva protección CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/usuarios/**").permitAll() // Permitir acceso a /api/usuarios
                .requestMatchers("/api/publicaciones").permitAll() // Permitir acceso a /api/publicaciones
                .anyRequest().authenticated() // Requiere autenticación en otros endpoints
            );

        return http.build();
    }
}