package com.vedruna.vedruna_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.dto.ExpoPushRequest;
import com.vedruna.vedruna_backend.services.DispositivoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @Operation(
        summary = "Registrar o dar de alta un dispositivo",
        description = "Registra un nuevo dispositivo con su ID ExpoPush o devuelve el existente asociado al usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dispositivo registrado o recuperado correctamente",
                     content = @Content(schema = @Schema(implementation = DispositivoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado o error en el registro"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
   @PostMapping("/registrar")
   public ResponseEntity<?> registrarODarDeAlta(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos del dispositivo a registrar o dar de alta",
        required = true,
        content = @Content(schema = @Schema(implementation = ExpoPushRequest.class))
    )
    @RequestBody ExpoPushRequest request) {
    try {
        DispositivoDTO dto = dispositivoService.obtenerODarDeAlta(request.getExpoPushId(), request.getUserId());
        return ResponseEntity.ok(dto);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
    }
}

}
