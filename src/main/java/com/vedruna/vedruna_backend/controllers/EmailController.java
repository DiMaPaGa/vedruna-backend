package com.vedruna.vedruna_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vedruna.vedruna_backend.services.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public String enviarCorreo(
        @RequestParam String destinatario,
        @RequestParam String asunto,
        @RequestParam String mensaje
    ) {
        emailService.sendEmail(destinatario, asunto, mensaje);
        return "Correo enviado a " + destinatario;
    }
}