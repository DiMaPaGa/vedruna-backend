package com.vedruna.vedruna_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.LikeRequestDTO;
import com.vedruna.vedruna_backend.services.LikeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Operation(
        summary = "Dar like a una publicación",
        description = "Permite a un usuario dar like a una publicación específica."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Like creado correctamente",
            content = @Content(schema = @Schema(implementation = LikeDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para crear el like")
    })
    @PostMapping
    public ResponseEntity<LikeDTO> darLike(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para crear un like",
            required = true,
            content = @Content(schema = @Schema(implementation = LikeRequestDTO.class))
        )
        @RequestBody LikeRequestDTO requestDTO) {
        return ResponseEntity.ok(likeService.darLike(requestDTO));
    }

    @Operation(
        summary = "Quitar like de una publicación",
        description = "Permite a un usuario quitar su like de una publicación."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Like eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Like no encontrado para el usuario o la publicación")
    })
    @DeleteMapping("/{userId}/{publicacionId}")
    public ResponseEntity<Void> quitarLike(
        @Parameter(description = "ID del usuario que quiere quitar el like") @PathVariable String userId,
        @Parameter(description = "ID de la publicación") @PathVariable Long publicacionId) {
        likeService.quitarLike(userId, publicacionId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Obtener likes de una publicación",
        description = "Obtiene la lista de likes de una publicación específica."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de likes obtenida correctamente",
            content = @Content(schema = @Schema(implementation = LikeDTO.class)))
    })
    @GetMapping("/{publicacionId}")
    public ResponseEntity<List<LikeDTO>> obtenerLikes(
        @Parameter(description = "ID de la publicación") @PathVariable Long publicacionId) {
        return ResponseEntity.ok(likeService.obtenerLikesDePublicacion(publicacionId));
    }
}
