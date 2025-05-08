package com.vedruna.vedruna_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // Permite todas las rutas
            .allowedOrigins("http://localhost:3000") // Cambia al origen de tu frontend
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
            .allowedHeaders("*") // Permite todos los encabezados
            .allowCredentials(true); // Permite cookies o credenciales
    }

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
                .requestMatchers("/api/usuarios-dispositivos/**").permitAll()
                .requestMatchers("/api/cleanup/**").permitAll() 
                .anyRequest().authenticated() // Requiere autenticación en otros endpoints
            );
        return http.build();
    }
}