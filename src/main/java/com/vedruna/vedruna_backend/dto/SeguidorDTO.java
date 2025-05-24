package com.vedruna.vedruna_backend.dto;


import com.vedruna.vedruna_backend.persistance.models.Estado;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa la relación de seguimiento entre usuarios")
public class SeguidorDTO {

    @Schema(description = "ID del usuario que sigue", example = "158684916549646")
    private String seguidorId;

    @Schema(description = "ID del usuario seguido", example = "1781651641649")
    private String seguidoId;

    @Schema(description = "Estado de la relación de seguimiento")
    private Estado estado;

    @Schema(description = "Nombre del seguidor", example = "Moisés Pastrana")
    private String nombreSeguidor;

    @Schema(description = "URL de la imagen del seguidor", example = "https://example.com/profile.jpg")
    private String imagenSeguidor;
}
