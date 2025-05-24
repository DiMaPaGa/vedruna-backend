package com.vedruna.vedruna_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Clase principal para iniciar la aplicación Vedruna Backend.
 * 
 * Esta clase carga las variables de entorno usando dotenv y arranca
 * la aplicación Spring Boot.
 */
@SpringBootApplication
public class VedrunaBackendApplication {

	/**
     * Método main para iniciar la aplicación.
     * Carga las variables de entorno antes de arrancar el contexto Spring.
     * 
     * @param args Argumentos de línea de comando (no utilizados).
     */
	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		// Asegurarnos de que las variables de entorno se han cargado correctamente
        String mailUsername = dotenv.get("MAIL_USERNAME");
        String mailPassword = dotenv.get("MAIL_PASSWORD");

		// Iniciar la aplicación
		SpringApplication.run(VedrunaBackendApplication.class, args);
	}

}
