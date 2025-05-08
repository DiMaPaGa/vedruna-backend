package com.vedruna.vedruna_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.services.HistoriaCleanupService;

@RestController
@RequestMapping("/api/cleanup")
public class HistoriaCleanupController {

    @Autowired
    private HistoriaCleanupService historiaCleanupService;

    // Endpoint para ejecutar manualmente la limpieza de historias expiradas
    @PostMapping("/limpiar-expiradas")
    public ResponseEntity<String> limpiarHistoriasExpiradas() {
        historiaCleanupService.limpiarHistoriasExpiradas();
        return ResponseEntity.ok("Limpieza de historias expiradas ejecutada manualmente");
    }
}
