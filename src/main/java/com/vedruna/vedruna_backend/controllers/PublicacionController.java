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
import com.vedruna.vedruna_backend.services.SeguidorService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;



    @PostMapping
    public ResponseEntity<?> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        try {
            // Verificar si el DTO está vacío o mal formado
            System.out.println("Recibiendo DTO: " + publicacionDTO);
            if (publicacionDTO == null || publicacionDTO.getTitulo() == null || publicacionDTO.getComentario() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faltan campos necesarios para la publicación.");
            }

            PublicacionDTO created = publicacionService.crearPublicacion(publicacionDTO);
             // Log para ver el DTO creado
        System.out.println("DTO creado: " + created);
            
            return new ResponseEntity<>(created, HttpStatus.CREATED);

        } catch (Exception e) {
            // Si ocurre un error, loguear el error y devolver un mensaje adecuado
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la publicación.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable Long id) throws ResourceNotFoundException {
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

    // Ruta para obtener todas las publicaciones públicas y privadas
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<PublicacionDTO>> obtenerTodasLasPublicaciones(@PathVariable String userId) {
        // Delegar la lógica de obtener publicaciones al servicio
        List<PublicacionDTO> publicaciones = publicacionService.obtenerTodasLasPublicaciones(userId);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }
}
    
