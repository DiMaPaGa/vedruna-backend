package com.vedruna.vedruna_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Clase de configuración para Swagger/OpenAPI.
 * Define la configuración principal de la documentación de la API REST,
 * incluyendo título, descripción, versión y datos de contacto.
 */
@Configuration 
public class OpenAPIConfig {

    /**
     * Crea y configura el bean OpenAPI para personalizar la documentación generada por Swagger.
     * 
     * @return objeto OpenAPI con la información básica de la API (título, descripción, versión y contacto)
     */
    @Bean
    public OpenAPI customOpenAPI() {
        System.out.println("Configuring Swagger API...");
        return new OpenAPI()
                .info(new Info()
                        .title("API Vedruna") 
                        .description("Backend de la red social educativa Vedruna") 
                        .version("1.0")
                        .contact(new Contact()
                                .name("Diana María Pascual")
                                .email("dianamariapascual@gmail.com") 
                        ));
    }
}