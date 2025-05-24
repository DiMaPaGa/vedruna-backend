package com.vedruna.vedruna_backend.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para asociar un usuario con un dispositivo")
public class UsuarioDispositivoDTO {

    @Schema(description = "ID del usuario (Google ID)", example = "156461626416984")
    private String userId;

    @Schema(description = "ID del dispositivo", example = "42")
    private Long dispositivoId;
    
}
