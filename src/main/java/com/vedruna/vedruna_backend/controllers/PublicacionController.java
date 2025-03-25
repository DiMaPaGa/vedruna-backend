package com.vedruna.vedruna_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.PublicacionDTO;
import com.vedruna.vedruna_backend.exceptions.ResourceNotFoundException;
import com.vedruna.vedruna_backend.services.PublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

     @PostMapping
    public ResponseEntity<PublicacionDTO> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        PublicacionDTO created = publicacionService.crearPublicacion(publicacionDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacion(@PathVariable Long id) throws ResourceNotFoundException {
        PublicacionDTO publicacionDTO = publicacionService.obtenerPublicacionPorId(id);
        return new ResponseEntity<>(publicacionDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PublicacionDTO>> obtenerPublicacionesPorUsuario(@PathVariable String userId) {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerPublicacionesPorUsuario(userId);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @GetMapping("/likes/{userId}")
    public ResponseEntity<List<PublicacionDTO>> obtenerPublicacionesConLikePorUsuario(@PathVariable String userId) {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerPublicacionesConLikePorUsuario(userId);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }
    
}
