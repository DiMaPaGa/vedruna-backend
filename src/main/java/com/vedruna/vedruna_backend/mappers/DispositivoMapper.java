package com.vedruna.vedruna_backend.mappers;


import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;

/**
 * Mapper para convertir entre la entidad Dispositivo y su DTO DispositivoDTO.
 */
@Component
public class DispositivoMapper {

    /**
     * Convierte un DispositivoDTO a la entidad Dispositivo.
     *
     * @param dto objeto DTO con datos del dispositivo
     * @return entidad Dispositivo con datos asignados desde el DTO
     */
    public Dispositivo toEntity(DispositivoDTO dto) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setExpoPushId(dto.getExpoPushId());
        return dispositivo;
    }

    /**
     * Convierte una entidad Dispositivo a un DispositivoDTO.
     *
     * @param entity entidad Dispositivo a convertir
     * @return DTO con los datos del dispositivo
     */
    public DispositivoDTO toDTO(Dispositivo entity) {
        return new DispositivoDTO(entity.getExpoPushId());
    }
}
    
