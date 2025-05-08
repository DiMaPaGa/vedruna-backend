package com.vedruna.vedruna_backend.mappers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.dto.HistoriaImagenDTO;
import com.vedruna.vedruna_backend.dto.HistoriaRequestDTO;
import com.vedruna.vedruna_backend.persistance.models.Historia;
import com.vedruna.vedruna_backend.persistance.models.HistoriaImagen;
import com.vedruna.vedruna_backend.persistance.models.Usuario;

@Component
public class HistoriaMapper {

    private final HistoriaImagenMapper imagenMapper;

    public HistoriaMapper(HistoriaImagenMapper imagenMapper) {
        this.imagenMapper = imagenMapper;
    }

    public HistoriaDTO toDTO(Historia entity) {
        if (entity == null) return null;

        List<HistoriaImagenDTO> imagenesDTO  = entity.getImagenes().stream()
            .map(imagenMapper::toDTO)
            .toList();

        return new HistoriaDTO(
            entity.getId(),
            entity.getAutor().getUserId(), // Google UID
            entity.getCreatedAt(),
            entity.getExpiraEn(),
            imagenesDTO 
        );
    }

    public Historia toEntity(HistoriaRequestDTO dto, Usuario autor) {
        if (dto == null) return null;

        Historia historia = new Historia();
        historia.setAutor(autor);
        historia.setExpiraEn(LocalDateTime.now().plusHours(24));

        List<HistoriaImagen> imagenes = dto.getImagenes().stream()
            .map(imagenDTO -> {
                HistoriaImagen img = imagenMapper.toEntity(imagenDTO);
                img.setHistoria(historia); // Establece la relación inversa
                return img;
            })
            .toList();

        historia.setImagenes(imagenes);
        return historia;
    }
}