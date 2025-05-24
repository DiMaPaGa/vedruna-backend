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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/historias")
public class HistoriaController {

    @Autowired
    private HistoriaService historiaService;

    @Operation(
        summary = "Crear una nueva historia",
        description = "Crea una historia con los datos proporcionados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Historia creada correctamente",
            content = @Content(schema = @Schema(implementation = HistoriaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para la creación")
    })
    @PostMapping
    public ResponseEntity<HistoriaDTO> createHistoria(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para crear una historia",
            required = true,
            content = @Content(schema = @Schema(implementation = HistoriaRequestDTO.class))
        )
        @RequestBody HistoriaRequestDTO requestDTO) {
        HistoriaDTO historiaDTO = historiaService.createHistoria(requestDTO);
        return new ResponseEntity<>(historiaDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todas las historias activas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de historias activas obtenida correctamente",
            content = @Content(schema = @Schema(implementation = HistoriaDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<HistoriaDTO>> getAllHistorias() {
        List<HistoriaDTO> historias = historiaService.getAllHistorias();
        return new ResponseEntity<>(historias, HttpStatus.OK);
    }

    @Operation(summary = "Obtener una historia por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Historia encontrada",
            content = @Content(schema = @Schema(implementation = HistoriaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Historia no encontrada")
    })
    @GetMapping("/{historiaId}")
    public ResponseEntity<HistoriaDTO> getHistoriaById(
        @Parameter(description = "ID de la historia") @PathVariable Long historiaId) {
        HistoriaDTO historiaDTO = historiaService.getHistoriaById(historiaId);
        return new ResponseEntity<>(historiaDTO, HttpStatus.OK);
        
    }

    @Operation(
        summary = "Eliminar una historia",
        description = "Elimina una historia identificada por su ID y el ID de usuario Google."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Historia eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Historia no encontrada o usuario inválido")
    })
    @DeleteMapping("/{historiaId}")
    public ResponseEntity<Void> deleteHistoria(
        @Parameter(description = "ID de la historia a eliminar") @PathVariable Long historiaId,
        @Parameter(description = "ID de usuario Google que realiza la eliminación") @RequestParam String userGoogleId) {
        historiaService.deleteHistoria(historiaId, userGoogleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
    
}
