package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.HistoriaImagenDTO;
import com.vedruna.vedruna_backend.persistance.models.HistoriaImagen;

/**
 * Mapper para convertir entre la entidad HistoriaImagen y su DTO HistoriaImagenDTO.
 */
@Component
public class HistoriaImagenMapper {

    /**
     * Convierte un DTO HistoriaImagenDTO a una entidad HistoriaImagen.
     *
     * @param dto el DTO a convertir
     * @return la entidad convertida o null si el DTO es null
     */
    public HistoriaImagen toEntity(HistoriaImagenDTO dto) {
        if (dto == null) return null;

        HistoriaImagen entity = new HistoriaImagen();
        entity.setImagenUrl(dto.getImagenUrl());
        entity.setTexto(dto.getTexto());
        // Asignar orden, si es null se asigna 0 por defecto
        entity.setOrden(dto.getOrden() != null ? dto.getOrden() : 0);
        return entity;
    }

    /**
     * Convierte una entidad HistoriaImagen a su DTO correspondiente.
     *
     * @param entity la entidad a convertir
     * @return el DTO resultante o null si la entidad es null
     */
    public HistoriaImagenDTO toDTO(HistoriaImagen entity) {
        if (entity == null) return null;

        return new HistoriaImagenDTO(
            entity.getImagenUrl(),
            entity.getTexto(),
            entity.getOrden()
        );
    }
}
