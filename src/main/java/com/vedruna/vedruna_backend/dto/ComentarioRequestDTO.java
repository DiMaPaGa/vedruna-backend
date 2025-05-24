package com.vedruna.vedruna_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la solicitud de creación o actualización de un comentario")
public class ComentarioRequestDTO {

    @Schema(description = "ID del usuario que realiza el comentario", example = "15748484848484", required = true)
    private String userId;

    @Schema(description = "ID de la publicación a la que pertenece el comentario", example = "789", required = true)
    private Long publicacionId;

    @Schema(description = "Contenido del comentario", example = "Este es un comentario", required = true)
    private String comentario;

    @Schema(description = "ID del comentario padre para respuestas anidadas", example = "15", nullable = true)
    private Long comentarioPadreId; // Opcional, puede ser null
}