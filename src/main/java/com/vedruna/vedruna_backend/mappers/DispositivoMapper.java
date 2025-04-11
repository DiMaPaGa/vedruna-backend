package com.vedruna.vedruna_backend.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

@Component
public class DispositivoMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Convertir Dispositivo a DispositivoDTO
    public DispositivoDTO toDTO(Dispositivo dispositivo) {
        DispositivoDTO dto = new DispositivoDTO();
        dto.setId(dispositivo.getId());
        dto.setUserId(dispositivo.getUsuario().getUserId());
        dto.setExpoPushToken(dispositivo.getExpoPushToken());
        dto.setCreatedAt(dispositivo.getCreatedAt());
        return dto;
    }

    // Convertir DispositivoDTO a Dispositivo (para persistencia)
    public Dispositivo toEntity(DispositivoDTO dto) {
        Dispositivo dispositivo = new Dispositivo();
        // ✅ Busca al Usuario por userId
        Usuario usuario = usuarioRepository.findByUserId(dto.getUserId())
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con userId: " + dto.getUserId()));

        dispositivo.setUsuario(usuario); // ✅ Establece la relación
        dispositivo.setExpoPushToken(dto.getExpoPushToken());
        dispositivo.setCreatedAt(dto.getCreatedAt());
        return dispositivo;
    }
    
}
