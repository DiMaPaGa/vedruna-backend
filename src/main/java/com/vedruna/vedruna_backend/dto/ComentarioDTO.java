package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa un comentario en una publicación, incluyendo respuestas anidadas y detalles del autor.")
public class ComentarioDTO {
    @Schema(description = "Identificador único del comentario", example = "25")
    private Long id;

    @Schema(description = "ID del usuario que hizo el comentario", example = "15548484587558")
    private String userId;

    @Schema(description = "ID de la publicación a la que pertenece el comentario", example = "789")
    private Long publicacionId;

    @Schema(description = "Texto del comentario", example = "Este es un comentario")
    private String comentario;

    @Schema(description = "ID del comentario padre para comentarios anidados", example = "12", nullable = true)
    private Long comentarioPadreId;

    @Schema(description = "Fecha y hora de creación del comentario", example = "2023-01-15T10:15:30")
    private LocalDateTime createdAt;

    @Schema(description = "Lista de respuestas anidadas al comentario")
    private List<ComentarioDTO> respuestas;

    @Schema(description = "Nombre del autor del comentario", example = "Diana Pascual")
    private String autorName;

    @Schema(description = "URL de la imagen de perfil del autor", example = "https://example.com/avatar.jpg")
    private String autorProfileImageUrl;
}