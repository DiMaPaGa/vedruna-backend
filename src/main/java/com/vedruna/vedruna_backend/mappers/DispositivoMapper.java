package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;

@Component
public class DispositivoMapper {

    // Convertir Dispositivo a DispositivoDTO
    public DispositivoDTO toDTO(Dispositivo dispositivo) {
        DispositivoDTO dto = new DispositivoDTO();
        dto.setId(dispositivo.getId());
        dto.setUserId(dispositivo.getUserId());
        dto.setExpoPushToken(dispositivo.getExpoPushToken());
        dto.setCreatedAt(dispositivo.getCreatedAt());
        return dto;
    }

    // Convertir DispositivoDTO a Dispositivo (para persistencia)
    public Dispositivo toEntity(DispositivoDTO dto) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setUserId(dto.getUserId());
        dispositivo.setExpoPushToken(dto.getExpoPushToken());
        dispositivo.setCreatedAt(dto.getCreatedAt());
        return dispositivo;
    }
    
}
