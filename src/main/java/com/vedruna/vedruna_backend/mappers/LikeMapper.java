package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.LikeRequestDTO;
import com.vedruna.vedruna_backend.persistance.models.Like;
import com.vedruna.vedruna_backend.persistance.models.LikeId;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;

/**
 * Mapper para convertir entre entidad Like y sus DTOs.
 */
@Component
public class LikeMapper {

    /**
     * Convierte una entidad Like a LikeDTO.
     *
     * @param like la entidad Like a convertir
     * @return el DTO correspondiente con userId y publicacionId
     */
    public LikeDTO toDTO(Like like) {
        LikeDTO dto = new LikeDTO();
        dto.setUserId(like.getLikeId().getUserId());
        dto.setPublicacionId(like.getLikeId().getPublicacionId());
        return dto;
    }

    /**
    * Convierte un LikeRequestDTO a la entidad Like.
    *
    * @param dto el DTO con datos para crear el Like
    * @param publicacion la publicación asociada al Like
    * @return la entidad Like creada con su ID compuesto
    */
    public Like toEntity(LikeRequestDTO dto, Publicacion publicacion) {
        Like like = new Like();
        LikeId likeId = new LikeId(dto.getUserId(), publicacion.getId());
        like.setLikeId(likeId);
        like.setPublicacion(publicacion);
        return like;
    }
    
}
