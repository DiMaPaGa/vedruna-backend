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

/**
 * Mapper para convertir entre la entidad Historia y sus DTOs correspondientes.
 */
@Component
public class HistoriaMapper {

    private final HistoriaImagenMapper imagenMapper;

    /**
     * Constructor con inyección del mapper de imágenes.
     * 
     * @param imagenMapper Mapper para HistoriaImagen
     */
    public HistoriaMapper(HistoriaImagenMapper imagenMapper) {
        this.imagenMapper = imagenMapper;
    }

    /**
     * Convierte una entidad Historia a su DTO HistoriaDTO.
     *
     * @param entity la entidad Historia a convertir
     * @return el DTO resultante o null si la entidad es null
     */
    public HistoriaDTO toDTO(Historia entity) {
        if (entity == null) return null;

        List<HistoriaImagenDTO> imagenesDTO  = entity.getImagenes().stream()
            .map(imagenMapper::toDTO)
            .toList();

        return new HistoriaDTO(
            entity.getId(),
            // Google UID del autor
            entity.getAutor().getUserId(), 
            entity.getCreatedAt(),
            entity.getExpiraEn(),
            imagenesDTO 
        );
    }

    /**
     * Convierte un DTO HistoriaRequestDTO a una entidad Historia.
     *
     * @param dto el DTO con los datos para crear la historia
     * @param autor el usuario autor de la historia
     * @return la entidad Historia creada o null si el DTO es null
     */
    public Historia toEntity(HistoriaRequestDTO dto, Usuario autor) {
        if (dto == null) return null;

        Historia historia = new Historia();
        historia.setAutor(autor);
        // La historia expira 24 horas después de la creación
        historia.setExpiraEn(LocalDateTime.now().plusHours(24));

        List<HistoriaImagen> imagenes = dto.getImagenes().stream()
            .map(imagenDTO -> {
                HistoriaImagen img = imagenMapper.toEntity(imagenDTO);
                img.setHistoria(historia); // Establece la relación bidireccional
                return img;
            })
            .toList();

        historia.setImagenes(imagenes);
        return historia;
    }
}