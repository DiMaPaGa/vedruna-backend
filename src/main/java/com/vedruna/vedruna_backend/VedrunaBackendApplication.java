package com.vedruna.vedruna_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class VedrunaBackendApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		// Asegurarnos de que las variables de entorno se han cargado correctamente
        String mailUsername = dotenv.get("MAIL_USERNAME");
        String mailPassword = dotenv.get("MAIL_PASSWORD");

        System.out.println("MAIL_USERNAME: " + mailUsername);
        System.out.println("MAIL_PASSWORD: " + mailPassword);

		SpringApplication.run(VedrunaBackendApplication.class, args);
	}

}
