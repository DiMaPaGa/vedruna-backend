package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.HistoriaImagenDTO;
import com.vedruna.vedruna_backend.persistance.models.HistoriaImagen;

@Component
public class HistoriaImagenMapper {

    public HistoriaImagen toEntity(HistoriaImagenDTO dto) {
        if (dto == null) return null;

        HistoriaImagen entity = new HistoriaImagen();
        entity.setImagenUrl(dto.getImagenUrl());
        entity.setTexto(dto.getTexto());
        entity.setOrden(dto.getOrden() != null ? dto.getOrden() : 0);
        return entity;
    }

    public HistoriaImagenDTO toDTO(HistoriaImagen entity) {
        if (entity == null) return null;

        return new HistoriaImagenDTO(
            entity.getImagenUrl(),
            entity.getTexto(),
            entity.getOrden()
        );
    }
}
