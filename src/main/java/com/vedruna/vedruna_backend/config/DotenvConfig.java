package com.vedruna.vedruna_backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para cargar variables de entorno desde un archivo .env.
 * Utiliza la librería Dotenv para gestionar estas variables.
 */
@Configuration
public class DotenvConfig {

    /**
     * Crea y configura un bean de tipo {@link Dotenv} que carga las variables
     * de entorno desde el archivo .env ubicado en el directorio raíz del proyecto.
     *
     * @return instancia configurada de {@link Dotenv} con las variables cargadas
     */
    @Bean
    public Dotenv dotenv() {
        return Dotenv.load();
    }
}