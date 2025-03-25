package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.LikeRequestDTO;
import com.vedruna.vedruna_backend.persistance.models.Like;
import com.vedruna.vedruna_backend.persistance.models.LikeId;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;

@Component
public class LikeMapper {
// Convertir Like a LikeDTO
public LikeDTO toDTO(Like like) {
    LikeDTO dto = new LikeDTO();
    dto.setUserId(like.getLikeId().getUserId());
    dto.setPublicacionId(like.getLikeId().getPublicacionId());
    return dto;
}

// Convertir LikeRequestDTO a Like
public Like toEntity(LikeRequestDTO dto, Publicacion publicacion) {
    Like like = new Like();
    LikeId likeId = new LikeId(dto.getUserId(), publicacion.getId());
    like.setLikeId(likeId);
    like.setPublicacion(publicacion);
    return like;
}
    
}
