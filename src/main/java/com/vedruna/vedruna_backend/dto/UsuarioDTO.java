package com.vedruna.vedruna_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String userId;  // ID de Google del usuario
    private String email;   // Correo electrónico del usuario
    private String givenName;  // Nombre del usuario (given_name de Google)
    private String profileImageUrl;  // URL de la foto de perfil
    
}
