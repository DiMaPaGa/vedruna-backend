package com.vedruna.vedruna_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de envío de correos electrónicos utilizando JavaMailSender.
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Envia un correo electrónico utilizando JavaMailSender.
     *
     * @param to el correo electrónico del destinatario.
     * @param subject el asunto del correo.
     * @param body el cuerpo del correo.
     */
    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}