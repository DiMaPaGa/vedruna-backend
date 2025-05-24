package com.vedruna.vedruna_backend.services;

/**
 * Servicio para el envío de correos electrónicos.
 */
public interface EmailService {

    /**
     * Envia un correo electrónico.
     *
     * @param to el correo electrónico del destinatario.
     * @param subject el asunto del correo.
     * @param body el cuerpo del correo.
     */
    void sendEmail(String to, String subject, String body);
}