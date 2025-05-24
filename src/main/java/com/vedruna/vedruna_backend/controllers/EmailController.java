package com.vedruna.vedruna_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vedruna.vedruna_backend.services.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Operation(
        summary = "Enviar un correo electrónico",
        description = "Permite enviar un correo electrónico especificando el destinatario, asunto y mensaje."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Correo enviado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/enviar")
    public String enviarCorreo(
        @Parameter(description = "Correo electrónico del destinatario", required = true)
        @RequestParam String destinatario,

        @Parameter(description = "Asunto del correo", required = true)
        @RequestParam String asunto,

        @Parameter(description = "Contenido del mensaje del correo", required = true)
        @RequestParam String mensaje
    ) {
        emailService.sendEmail(destinatario, asunto, mensaje);
        return "Correo enviado a " + destinatario;
    }
}