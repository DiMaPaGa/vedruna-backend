package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.persistance.models.Estado;
import com.vedruna.vedruna_backend.persistance.models.Seguidor;
import com.vedruna.vedruna_backend.persistance.models.SeguidorId;

/**
 * Mapper para convertir entre entidad Seguidor y su DTO.
 */
@Component
public class SeguidorMapper {

    /**
     * Convierte la entidad Seguidor a su correspondiente DTO.
     *
     * @param seguidor la entidad Seguidor a convertir
     * @return el DTO con los datos del seguidor
     */
    public SeguidorDTO toDTO(Seguidor seguidor) {
        SeguidorDTO dto = new SeguidorDTO();
        dto.setSeguidorId(seguidor.getId().getSeguidorId());
        dto.setSeguidoId(seguidor.getId().getSeguidoId());
        dto.setEstado(seguidor.getEstado());
        if (seguidor.getSeguidor() != null) {
            dto.setNombreSeguidor(seguidor.getSeguidor().getGivenName());
            dto.setImagenSeguidor(seguidor.getSeguidor().getProfileImageUrl());
        }
        return dto;
    }

    /**
     * Convierte un DTO SeguidorDTO a la entidad Seguidor.
     *
     * @param dto el DTO con los datos a convertir
     * @return la entidad Seguidor creada a partir del DTO
     */
    public Seguidor toEntity(SeguidorDTO dto) {
        Seguidor seguidor = new Seguidor();
        SeguidorId id = new SeguidorId(dto.getSeguidorId(), dto.getSeguidoId());
        seguidor.setId(id);
        seguidor.setEstado(dto.getEstado() != null ? dto.getEstado() : Estado.PENDIENTE);
        
        return seguidor;
    }
    
}
