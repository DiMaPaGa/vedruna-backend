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

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/seguidores")
public class SeguidorController {

    @Autowired
    private SeguidorService seguidorService;

     // Obtener los seguidores de un usuario con estado específico, paginados
     @GetMapping("/seguidores/{seguidoId}")
     public ResponseEntity<Page<SeguidorDTO>> obtenerSeguidores(
             @PathVariable String seguidoId,
             @RequestParam Estado estado,
             Pageable pageable) {
         Page<SeguidorDTO> seguidores = seguidorService.obtenerSeguidores(seguidoId, estado, pageable);
         return new ResponseEntity<>(seguidores, HttpStatus.OK);
     }
 
     // Obtener los usuarios seguidos por un usuario con estado específico, paginados
     @GetMapping("/seguidos/{seguidorId}")
     public ResponseEntity<Page<SeguidorDTO>> obtenerSeguidos(
             @PathVariable String seguidorId,
             @RequestParam Estado estado,
             Pageable pageable) {
         Page<SeguidorDTO> seguidos = seguidorService.obtenerSeguidos(seguidorId, estado, pageable);
         return new ResponseEntity<>(seguidos, HttpStatus.OK);
     }
 
     // Verificar si un usuario sigue a otro
     @GetMapping("/es-seguidor")
     public ResponseEntity<Boolean> esSeguidor(
             @RequestParam String seguidorId,
             @RequestParam String seguidoId) {
         boolean esSeguidor = seguidorService.esSeguidor(seguidorId, seguidoId);
         return new ResponseEntity<>(esSeguidor, HttpStatus.OK);
     }
 
     // Contar el número de seguidores de un usuario con estado específico
     @GetMapping("/contar-seguidores/{seguidoId}")
     public ResponseEntity<Long> contarSeguidores(
             @PathVariable String seguidoId,
             @RequestParam Estado estado) {
         long cantidadSeguidores = seguidorService.contarSeguidores(seguidoId, estado);
         return new ResponseEntity<>(cantidadSeguidores, HttpStatus.OK);
     }
 
     // Contar el número de usuarios seguidos por un usuario con estado específico
     @GetMapping("/contar-seguidos/{seguidorId}")
     public ResponseEntity<Long> contarSeguidos(
             @PathVariable String seguidorId,
             @RequestParam Estado estado) {
         long cantidadSeguidos = seguidorService.contarSeguidos(seguidorId, estado);
         return new ResponseEntity<>(cantidadSeguidos, HttpStatus.OK);
     }
 
     // Seguir a un usuario
     @PostMapping("/seguir")
     public ResponseEntity<String> seguirUsuario(
             @RequestParam String seguidorId,
             @RequestParam String seguidoId) {
         seguidorService.seguirUsuario(seguidorId, seguidoId);
         return new ResponseEntity<>("Usuario seguido con éxito.", HttpStatus.CREATED);
     }
 
     // Eliminar un seguimiento
     @DeleteMapping("/dejar-de-seguir")
     public ResponseEntity<String> dejarDeSeguir(
             @RequestParam String seguidorId,
             @RequestParam String seguidoId) {
         seguidorService.dejarDeSeguir(seguidorId, seguidoId);
         return new ResponseEntity<>("Dejaste de seguir a este usuario.", HttpStatus.OK);
     }
 
     // Aceptar una solicitud de seguimiento
     @PutMapping("/aceptar-solicitud")
     public ResponseEntity<String> aceptarSolicitud(
             @RequestParam String seguidorId,
             @RequestParam String seguidoId) {
         seguidorService.aceptarSolicitud(seguidorId, seguidoId);
         return new ResponseEntity<>("Solicitud de seguimiento aceptada.", HttpStatus.OK);
     }

}
