package com.vedruna.vedruna_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.ComentarioDTO;
import com.vedruna.vedruna_backend.dto.ComentarioRequestDTO;
import com.vedruna.vedruna_backend.exceptions.ComentarioNotFoundException;
import com.vedruna.vedruna_backend.services.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Operation(summary = "Obtener todos los comentarios de una publicación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comentarios obtenidos correctamente"),
        @ApiResponse(responseCode = "404", description = "Publicación no encontrada")
    })

     @GetMapping("/publicacion/{publicacionId}")
     public List<ComentarioDTO> obtenerComentariosPorPublicacion(
        @Parameter(description = "ID de la publicación") @PathVariable Long publicacionId) {
         return comentarioService.obtenerComentariosPorPublicacion(publicacionId);
     }
 
     @Operation(summary = "Obtener comentarios de una publicación ordenados por fecha")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comentarios obtenidos correctamente")
    })

     @GetMapping("/publicacion/{publicacionId}/ordenados")
     public List<ComentarioDTO> obtenerComentariosPorPublicacionOrdenados(
        @Parameter(description = "ID de la publicación") @PathVariable Long publicacionId) {
         return comentarioService.obtenerComentariosPorPublicacionOrdenados(publicacionId);
     }
 
     @Operation(summary = "Obtener respuestas a un comentario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Respuestas obtenidas correctamente")
    })

     @GetMapping("/respuesta/{comentarioPadreId}")
     public List<ComentarioDTO> obtenerRespuestas(
        @Parameter(description = "ID del comentario padre") @PathVariable Long comentarioPadreId) {
         return comentarioService.obtenerRespuestas(comentarioPadreId);
     }
 
    @Operation(summary = "Obtener un comentario por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comentario encontrado"),
        @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @GetMapping("/{id}")
    public ComentarioDTO obtenerComentarioPorId(
        @Parameter(description = "ID del comentario") @PathVariable Long id) {
         return comentarioService.obtenerPorId(id)
            .orElseThrow(() -> new ComentarioNotFoundException("Comentario con ID " + id + " no encontrado"));
    }
 
      @Operation(summary = "Crear un nuevo comentario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Comentario creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud malformada o datos inválidos")
    })
     @PostMapping
     public ComentarioDTO crearComentario(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del nuevo comentario a crear",
            required = true,
            content = @Content(schema = @Schema(implementation = ComentarioRequestDTO.class))
        )
        @RequestBody ComentarioRequestDTO comentarioRequestDTO) {
        return comentarioService.guardarComentario(comentarioRequestDTO);
    }
 
     @Operation(
        summary = "Eliminar un comentario por su ID",
        description = "Elimina un comentario existente por su ID. Si el comentario no existe, se devuelve un error 404."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Comentario eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
     @DeleteMapping("/{id}")
     public void eliminarComentario(
        @Parameter(description = "ID del comentario a eliminar") @PathVariable Long id) {
         comentarioService.eliminarComentario(id);
     }
    
}
