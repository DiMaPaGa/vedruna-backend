package com.vedruna.vedruna_backend.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.services.HistoriaService;

@RestController
@RequestMapping("/api/historias")
public class HistoriaController {

    @Autowired
    private HistoriaService historiaService;

    @PostMapping
    public ResponseEntity<HistoriaDTO> guardarHistoria(@RequestBody HistoriaDTO historiaDTO) {
        HistoriaDTO historiaGuardada = historiaService.guardarHistoria(historiaDTO);
        return ResponseEntity.ok(historiaGuardada);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HistoriaDTO>> obtenerHistoriasPorUserId(@PathVariable String userId) {
        List<HistoriaDTO> historias = historiaService.obtenerHistoriasPorUserId(userId);
        return ResponseEntity.ok(historias);
    }

    @GetMapping("/expiradas")
    public ResponseEntity<List<HistoriaDTO>> obtenerHistoriasNoExpiradas() {
        List<HistoriaDTO> historias = historiaService.obtenerHistoriasNoExpiradas(LocalDateTime.now());
        return ResponseEntity.ok(historias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaDTO> obtenerHistoriaPorId(@PathVariable Long id) {
        Optional<HistoriaDTO> historiaDTO = historiaService.obtenerHistoriaPorId(id);
        return historiaDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistoria(@PathVariable Long id) {
        historiaService.eliminarHistoria(id);
        return ResponseEntity.noContent().build();
    }
    
}
