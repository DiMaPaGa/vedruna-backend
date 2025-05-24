package com.vedruna.vedruna_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.services.HistoriaCleanupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/cleanup")
public class HistoriaCleanupController {

    @Autowired
    private HistoriaCleanupService historiaCleanupService;

    @Operation(
        summary = "Ejecutar limpieza manual de historias expiradas",
        description = "Permite ejecutar manualmente el proceso que limpia las historias que han expirado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Limpieza ejecutada correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno al ejecutar la limpieza")
    })
    @PostMapping("/limpiar-expiradas")
    public ResponseEntity<String> limpiarHistoriasExpiradas() {
        historiaCleanupService.limpiarHistoriasExpiradas();
        return ResponseEntity.ok("Limpieza de historias expiradas ejecutada manualmente");
    }
}
