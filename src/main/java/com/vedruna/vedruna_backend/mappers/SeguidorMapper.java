package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.persistance.models.Seguidor;
import com.vedruna.vedruna_backend.persistance.models.SeguidorId;

@Component
public class SeguidorMapper {

    public SeguidorDTO toDTO(Seguidor seguidor) {
        SeguidorDTO dto = new SeguidorDTO();
        dto.setSeguidorId(seguidor.getId().getSeguidorId());
        dto.setSeguidoId(seguidor.getId().getSeguidoId());
        dto.setCreatedAt(seguidor.getCreatedAt());
        return dto;
    }

    public Seguidor toEntity(SeguidorDTO dto) {
        Seguidor seguidor = new Seguidor();
        SeguidorId id = new SeguidorId(dto.getSeguidorId(), dto.getSeguidoId());
        seguidor.setId(id);
        seguidor.setCreatedAt(dto.getCreatedAt());
        return seguidor;
    }
    
}
