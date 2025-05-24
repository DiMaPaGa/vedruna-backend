package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.models.UsuarioDispositivo;
import com.vedruna.vedruna_backend.dto.UsuarioDispositivoDTO;

/**
 * Mapper para convertir entre la entidad UsuarioDispositivo y su DTO.
 */
@Component
public class UsuarioDispositivoMapper {

    /**
     * Convierte Usuario y Dispositivo en una entidad UsuarioDispositivo.
     *
     * @param usuario entidad Usuario
     * @param dispositivo entidad Dispositivo
     * @return entidad UsuarioDispositivo asociando usuario y dispositivo
     */
    public UsuarioDispositivo toEntity(Usuario usuario, Dispositivo dispositivo) {
        UsuarioDispositivo usuarioDispositivo = new UsuarioDispositivo();
        usuarioDispositivo.setUsuario(usuario);
        usuarioDispositivo.setDispositivo(dispositivo);
        return usuarioDispositivo;
    }

    /**
     * Convierte una entidad UsuarioDispositivo a su DTO correspondiente.
     *
     * @param entity entidad UsuarioDispositivo
     * @return DTO con userId y dispositivoId
     */
    public UsuarioDispositivoDTO toDto(UsuarioDispositivo entity) {
        UsuarioDispositivoDTO dto = new UsuarioDispositivoDTO();
        dto.setUserId(entity.getUsuario().getUserId());
        dto.setDispositivoId(entity.getDispositivo().getId());
        return dto;
    }
    
}
