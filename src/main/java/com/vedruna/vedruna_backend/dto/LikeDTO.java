package com.vedruna.vedruna_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa un 'like' en una publicación")
public class LikeDTO {

    @Schema(description = "ID del usuario que da el like", example = "178465161615")
    private String userId;

    @Schema(description = "ID de la publicación que recibe el like", example = "1001")
    private Long publicacionId;

}
