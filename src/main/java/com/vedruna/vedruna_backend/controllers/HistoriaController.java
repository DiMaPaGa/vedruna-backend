package com.vedruna.vedruna_backend.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.dto.HistoriaRequestDTO;
import com.vedruna.vedruna_backend.services.HistoriaService;

@RestController
@RequestMapping("/api/historias")
public class HistoriaController {

    @Autowired
    private HistoriaService historiaService;

    // Crear una nueva historia
    @PostMapping
    public ResponseEntity<HistoriaDTO> createHistoria(@RequestBody HistoriaRequestDTO requestDTO) {
        HistoriaDTO historiaDTO = historiaService.createHistoria(requestDTO);
        return new ResponseEntity<>(historiaDTO, HttpStatus.CREATED);
    }

    // Obtener todas las historias activas
    @GetMapping
    public ResponseEntity<List<HistoriaDTO>> getAllHistorias() {
        List<HistoriaDTO> historias = historiaService.getAllHistorias();
        return new ResponseEntity<>(historias, HttpStatus.OK);
    }

    // Obtener una historia por ID
    @GetMapping("/{historiaId}")
    public ResponseEntity<HistoriaDTO> getHistoriaById(@PathVariable Long historiaId) {
        HistoriaDTO historiaDTO = historiaService.getHistoriaById(historiaId);
            return new ResponseEntity<>(historiaDTO, HttpStatus.OK);
        
    }

    // Eliminar una historia
    @DeleteMapping("/{historiaId}")
    public ResponseEntity<Void> deleteHistoria(@PathVariable Long historiaId,
                                               @RequestParam String userGoogleId) {
        historiaService.deleteHistoria(historiaId, userGoogleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
    
}
