package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.models.UsuarioDispositivo;
import com.vedruna.vedruna_backend.dto.UsuarioDispositivoDTO;

@Component
public class UsuarioDispositivoMapper {
    public UsuarioDispositivo toEntity(Usuario usuario, Dispositivo dispositivo) {
        UsuarioDispositivo usuarioDispositivo = new UsuarioDispositivo();
        usuarioDispositivo.setUsuario(usuario);
        usuarioDispositivo.setDispositivo(dispositivo);
        return usuarioDispositivo;
    }

    public UsuarioDispositivoDTO toDto(UsuarioDispositivo entity) {
        UsuarioDispositivoDTO dto = new UsuarioDispositivoDTO();
        dto.setUserId(entity.getUsuario().getUserId());
        dto.setDispositivoId(entity.getDispositivo().getId());
        return dto;
    }
    
}
