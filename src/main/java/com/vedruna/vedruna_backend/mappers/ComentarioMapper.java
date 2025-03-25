package com.vedruna.vedruna_backend.mappers;


import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.ComentarioDTO;
import com.vedruna.vedruna_backend.dto.ComentarioRequestDTO;
import com.vedruna.vedruna_backend.persistance.models.Comentario;

@Component
public class ComentarioMapper {

    public ComentarioDTO toDTO(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(comentario.getId());
        dto.setUserId(comentario.getUserId());
        dto.setPublicacionId(comentario.getPublicacion().getId());
        dto.setComentario(comentario.getComentario());
        dto.setComentarioPadreId(comentario.getComentarioPadre() != null ? comentario.getComentarioPadre().getId() : null);
        dto.setCreatedAt(comentario.getCreatedAt());

        if (comentario.getRespuestas() != null) {
            dto.setRespuestas(comentario.getRespuestas().stream().map(this::toDTO).toList());
        }

        return dto;
    }

    public Comentario toEntity(ComentarioRequestDTO dto) {
        Comentario comentario = new Comentario();
        comentario.setUserId(dto.getUserId());
        comentario.setComentario(dto.getComentario());
        return comentario;
    }
    
}
