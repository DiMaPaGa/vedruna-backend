package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.persistance.models.Usuario;

/**
 * Mapper para convertir entre la entidad Usuario y su DTO UsuarioDTO.
 */
@Component
public class UsuarioMapper {

     /**
     * Convierte un objeto Usuario a UsuarioDTO.
     * 
     * @param usuario la entidad Usuario
     * @return el DTO UsuarioDTO o null si el usuario es null
     */
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

    /**
     * Convierte un UsuarioDTO a la entidad Usuario.
     * 
     * @param usuarioDTO el DTO UsuarioDTO
     * @return la entidad Usuario o null si el DTO es null
     */
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
