package com.vedruna.vedruna_backend.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Clase de configuración para el servicio de envío de correos electrónicos.
 * Configura el {@link JavaMailSender} con los parámetros necesarios para
 * conectarse a un servidor SMTP (Gmail en este caso).
 */
@Configuration
public class MailConfig {

    /**
     * Crea y configura un bean de {@link JavaMailSender} para el envío de emails.
     * Obtiene las credenciales (usuario y contraseña) desde las variables de entorno
     * MAIL_USERNAME y MAIL_PASSWORD.
     * Configura el host, puerto y las propiedades SMTP necesarias para autenticación
     * y cifrado STARTTLS.
     * 
     * @return instancia configurada de {@link JavaMailSender}
     */
     @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Cargar las credenciales desde las variables de entorno
        String mailUsername = System.getenv("MAIL_USERNAME");
        String mailPassword = System.getenv("MAIL_PASSWORD");

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        // Configurar las propiedades adicionales de SMTP
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
    
}
