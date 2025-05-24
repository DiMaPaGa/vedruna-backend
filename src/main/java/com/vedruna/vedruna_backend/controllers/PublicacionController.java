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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;


    @Operation(
        summary = "Crear una nueva publicación",
        description = "Permite a un usuario crear una publicación si el DTO está correctamente formado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Publicación creada correctamente",
            content = @Content(schema = @Schema(implementation = PublicacionDTO.class))),
        @ApiResponse(responseCode = "400", description = "Faltan campos necesarios en la solicitud"),
        @ApiResponse(responseCode = "500", description = "Error interno al crear la publicación")
    })
    @PostMapping
    public ResponseEntity<?> crearPublicacion(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la publicación a crear",
            required = true,
            content = @Content(schema = @Schema(implementation = PublicacionDTO.class))
        )
        @RequestBody PublicacionDTO publicacionDTO) {
        try {
            if (publicacionDTO == null || publicacionDTO.getTitulo() == null || publicacionDTO.getComentario() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faltan campos necesarios para la publicación.");
            }

            PublicacionDTO created = publicacionService.crearPublicacion(publicacionDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);

        } catch (Exception e) {
            // Si ocurre un error, loguear el error y devolver un mensaje adecuado
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la publicación.");
        }
    }

    @Operation(
        summary = "Obtener una publicación por ID",
        description = "Devuelve los detalles de una publicación dado su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publicación encontrada",
            content = @Content(schema = @Schema(implementation = PublicacionDTO.class))),
        @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(
        @Parameter(description = "ID de la publicación") @PathVariable Long id) throws ResourceNotFoundException {
        PublicacionDTO publicacionDTO = publicacionService.obtenerPublicacionPorId(id);
        return new ResponseEntity<>(publicacionDTO, HttpStatus.OK);
    }

    @Operation(
        summary = "Obtener publicaciones por usuario",
        description = "Devuelve todas las publicaciones realizadas por un usuario específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de publicaciones del usuario obtenida correctamente")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PublicacionDTO>> obtenerPublicacionesPorUsuario(
        @Parameter(description = "ID del usuario") @PathVariable String userId) {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerPublicacionesPorUsuario(userId);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @Operation(
        summary = "Obtener publicaciones con like por usuario",
        description = "Devuelve todas las publicaciones que han sido marcadas con like por un usuario específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de publicaciones con like obtenida correctamente")
    })
    @GetMapping("/likes/{userId}")
    public ResponseEntity<List<PublicacionDTO>> obtenerPublicacionesConLikePorUsuario(
        @Parameter(description = "ID del usuario") @PathVariable String userId) {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerPublicacionesConLikePorUsuario(userId);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @Operation(
        summary = "Obtener todas las publicaciones visibles para un usuario",
        description = "Obtiene todas las publicaciones públicas y privadas visibles para un usuario específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista completa de publicaciones obtenida correctamente")
    })
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<PublicacionDTO>> obtenerTodasLasPublicaciones(
        @Parameter(description = "ID del usuario") @PathVariable String userId) {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerTodasLasPublicaciones(userId);
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }
}
    
