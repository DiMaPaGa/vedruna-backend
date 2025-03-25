package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.PublicacionDTO;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;

@Component
public class PublicacionMapper {

    public PublicacionDTO toDTO(Publicacion publicacion) {
        PublicacionDTO dto = new PublicacionDTO();
        dto.setId(publicacion.getId());
        dto.setUserId(publicacion.getUserId());
        dto.setImageUrl(publicacion.getImageUrl());
        dto.setTitulo(publicacion.getTitulo());
        dto.setComentario(publicacion.getComentario());
        dto.setPrivacidad(publicacion.getPrivacidad());
        dto.setCreatedAt(publicacion.getCreatedAt());
        return dto;
    }

    public Publicacion toEntity(PublicacionDTO dto) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(dto.getId());
        publicacion.setUserId(dto.getUserId());
        publicacion.setImageUrl(dto.getImageUrl());
        publicacion.setTitulo(dto.getTitulo());
        publicacion.setComentario(dto.getComentario());
        publicacion.setPrivacidad(dto.getPrivacidad());
        publicacion.setCreatedAt(dto.getCreatedAt());
        return publicacion;
    }
    
}
