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
                .requestMatchers("/api/publicaciones/**").permitAll() // Permitir acceso a /api/publicaciones
                .requestMatchers("/api/seguidores/**").permitAll() // Permitir acceso a /api/seguidores
                .requestMatchers("/api/likes/**").permitAll() // Permitir acceso a /api/likes
                .requestMatchers("/api/historias/**").permitAll() // Permitir acceso a /api/historias
                .requestMatchers("/api/dispositivos/**").permitAll() // Permitir acceso a /api/dispositivos
                .requestMatchers("/api/comentarios/**").permitAll() // Permitir acceso a /api/comentarios
                .requestMatchers("/api/tickets/**").permitAll() // Permitir acceso a /api/tickets
                .requestMatchers("/api/email/enviar").permitAll() 
                .anyRequest().authenticated() // Requiere autenticación en otros endpoints
            );

        return http.build();
    }
}