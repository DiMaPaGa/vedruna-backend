package com.vedruna.vedruna_backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.persistance.models.Estado;
import com.vedruna.vedruna_backend.services.SeguidorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/seguidores")
public class SeguidorController {

    @Autowired
    private SeguidorService seguidorService;

    @Operation(summary = "Obtener seguidores", description = "Obtiene una lista paginada de seguidores de un usuario con un estado específico.")
    @GetMapping("/seguidores/{seguidoId}")
     public ResponseEntity<Page<SeguidorDTO>> obtenerSeguidores(
            @Parameter(description = "ID del usuario seguido") @PathVariable String seguidoId,
            @Parameter(description = "Estado del seguimiento") @RequestParam Estado estado,
            Pageable pageable) {
         Page<SeguidorDTO> seguidores = seguidorService.obtenerSeguidores(seguidoId, estado, pageable);
         return new ResponseEntity<>(seguidores, HttpStatus.OK);
     }
 
     @Operation(summary = "Obtener seguidos", description = "Obtiene una lista paginada de usuarios seguidos por un usuario con un estado específico.")
     @GetMapping("/seguidos/{seguidorId}")
     public ResponseEntity<Page<SeguidorDTO>> obtenerSeguidos(
            @Parameter(description = "ID del seguidor") @PathVariable String seguidorId,
            @Parameter(description = "Estado del seguimiento") @RequestParam Estado estado,
            Pageable pageable) {
         Page<SeguidorDTO> seguidos = seguidorService.obtenerSeguidos(seguidorId, estado, pageable);
         return new ResponseEntity<>(seguidos, HttpStatus.OK);
     }
 
     @Operation(summary = "Verificar seguimiento", description = "Verifica si un usuario sigue a otro.")
     @GetMapping("/es-seguidor")
     public ResponseEntity<Boolean> esSeguidor(
            @Parameter(description = "ID del seguidor") @RequestParam String seguidorId,
            @Parameter(description = "ID del seguido") @RequestParam String seguidoId) {
         boolean esSeguidor = seguidorService.esSeguidor(seguidorId, seguidoId);
         return new ResponseEntity<>(esSeguidor, HttpStatus.OK);
     }
 
     @Operation(summary = "Contar seguidores", description = "Cuenta el número de seguidores de un usuario con un estado específico.")
     @GetMapping("/contar-seguidores/{seguidoId}")
     public ResponseEntity<Long> contarSeguidores(
            @Parameter(description = "ID del seguido") @PathVariable String seguidoId,
            @Parameter(description = "Estado del seguimiento") @RequestParam Estado estado) {
         long cantidadSeguidores = seguidorService.contarSeguidores(seguidoId, estado);
         return new ResponseEntity<>(cantidadSeguidores, HttpStatus.OK);
     }
 
     @Operation(summary = "Contar seguidos", description = "Cuenta el número de usuarios seguidos por un usuario con un estado específico.")
    @GetMapping("/contar-seguidos/{seguidorId}")
     public ResponseEntity<Long> contarSeguidos(
            @Parameter(description = "ID del seguidor") @PathVariable String seguidorId,
            @Parameter(description = "Estado del seguimiento") @RequestParam Estado estado) {
         long cantidadSeguidos = seguidorService.contarSeguidos(seguidorId, estado);
         return new ResponseEntity<>(cantidadSeguidos, HttpStatus.OK);
     }
 
     @Operation(summary = "Seguir usuario", description = "Permite a un usuario seguir a otro.")
     @PostMapping("/seguir")
     public ResponseEntity<String> seguirUsuario(
            @Parameter(description = "ID del seguidor") @RequestParam String seguidorId,
            @Parameter(description = "ID del seguido") @RequestParam String seguidoId) {
         seguidorService.seguirUsuario(seguidorId, seguidoId);
         return new ResponseEntity<>("Usuario seguido con éxito.", HttpStatus.CREATED);
     }
 
     @Operation(summary = "Dejar de seguir", description = "Permite a un usuario dejar de seguir a otro.")
     @DeleteMapping("/dejar-de-seguir")
     public ResponseEntity<String> dejarDeSeguir(
            @Parameter(description = "ID del seguidor") @RequestParam String seguidorId,
            @Parameter(description = "ID del seguido") @RequestParam String seguidoId) {
         seguidorService.dejarDeSeguir(seguidorId, seguidoId);
         return new ResponseEntity<>("Dejaste de seguir a este usuario.", HttpStatus.OK);
     }
 
     @Operation(summary = "Aceptar solicitud de seguimiento", description = "Permite aceptar una solicitud de seguimiento pendiente.")
     @PutMapping("/aceptar-solicitud")
     public ResponseEntity<String> aceptarSolicitud(
            @Parameter(description = "ID del seguidor") @RequestParam String seguidorId,
            @Parameter(description = "ID del seguido") @RequestParam String seguidoId) {
         seguidorService.aceptarSolicitud(seguidorId, seguidoId);
         return new ResponseEntity<>("Solicitud de seguimiento aceptada.", HttpStatus.OK);
     }

}
