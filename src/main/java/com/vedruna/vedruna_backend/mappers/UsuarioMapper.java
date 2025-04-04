package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.persistance.models.Usuario;

@Component
public class UsuarioMapper {

     // Convertir modelo Usuario a DTO UsuarioDTO
    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
                usuario.getUserId(),
                usuario.getEmail(),
                usuario.getGivenName(),
                usuario.getProfileImageUrl()
        );
    }

    // Convertir DTO UsuarioDTO a modelo Usuario
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setUserId(usuarioDTO.getUserId());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setGivenName(usuarioDTO.getGivenName());
        usuario.setProfileImageUrl(usuarioDTO.getProfileImageUrl());
        return usuario;
    }
    
}
