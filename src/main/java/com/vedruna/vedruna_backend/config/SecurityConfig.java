package com.vedruna.vedruna_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración de seguridad para la aplicación.
 * 
 * Esta clase configura CORS para permitir solicitudes desde el frontend
 * y deshabilita temporalmente la seguridad HTTP para facilitar pruebas.
 * 
 * Implementa WebMvcConfigurer para configurar CORS globalmente.
 */
@Configuration
public class SecurityConfig implements WebMvcConfigurer{

    /**
     * Configura los mapeos CORS para permitir solicitudes desde el frontend.
     * 
     * @param registry el registro para añadir las configuraciones CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // Permite todas las rutas
            .allowedOrigins("http://localhost:3000") // Cambia al origen del frontend
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
            .allowedHeaders("*") // Permite todos los encabezados
            .allowCredentials(true); // Permite cookies o credenciales
    }

    /**
     * Configura la cadena de filtros de seguridad HTTP.
     * 
     * Actualmente deshabilita CSRF y permite todas las solicitudes para facilitar pruebas.
     * 
     * @param http el objeto HttpSecurity para configurar la seguridad web
     * @return el SecurityFilterChain configurado
     * @throws Exception en caso de errores de configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Deshabilita toda la seguridad para pruebas
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        return http.build();
    }
}
        