package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.persistance.models.Historia;

@Component
public class HistoriaMapper {
    // Mapea de Historia a HistoriaDTO
    public HistoriaDTO toDTO(Historia historia) {
        HistoriaDTO dto = new HistoriaDTO();
        dto.setId(historia.getId());
        dto.setUserId(historia.getAutor().getUserId());
        dto.setImageUrl(historia.getImageUrl());
        dto.setTexto(historia.getTexto());
        dto.setCreatedAt(historia.getCreatedAt());
        dto.setExpiraEn(historia.getExpiraEn());
        return dto;
    }

    // Mapea de HistoriaDTO a Historia
    public Historia toEntity(HistoriaDTO historiaDTO) {
        Historia historia = new Historia();
        historia.setImageUrl(historiaDTO.getImageUrl());
        historia.setTexto(historiaDTO.getTexto());
        historia.setExpiraEn(historiaDTO.getExpiraEn());
        return historia;
    }
}
