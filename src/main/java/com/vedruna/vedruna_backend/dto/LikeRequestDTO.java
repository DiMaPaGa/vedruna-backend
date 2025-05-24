package com.vedruna.vedruna_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la solicitud de un 'like' en una publicación")
public class LikeRequestDTO {
    @Schema(description = "ID del usuario que da el like", example = "17891561616161065")
    private String userId;

    @Schema(description = "ID de la publicación a la que se da like", example = "1001")
    private Long publicacionId;  
}
