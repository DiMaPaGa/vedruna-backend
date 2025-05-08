package com.vedruna.vedruna_backend.mappers;


import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;


@Component
public class DispositivoMapper {

    public Dispositivo toEntity(DispositivoDTO dto) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setExpoPushId(dto.getExpoPushId());
        return dispositivo;
    }

    public DispositivoDTO toDTO(Dispositivo entity) {
        return new DispositivoDTO(entity.getExpoPushId());
    }
}
    
