package com.vedruna.vedruna_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa la información de un usuario")
public class UsuarioDTO {

    @Schema(description = "ID de Google del usuario", example = "1234567890abcdef")
    private String userId;

    @Schema(description = "Correo electrónico del usuario", example = "usuario@ejemplo.com")
    private String email;

    @Schema(description = "Nombre del usuario (given_name de Google)", example = "Ana")
    private String givenName;

    @Schema(description = "URL de la foto de perfil del usuario", example = "https://example.com/profile.jpg")
    private String profileImageUrl;
    
}
