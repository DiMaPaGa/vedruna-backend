package com.vedruna.vedruna_backend.mappers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.PublicacionDTO;
import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

/**
 * Mapper para convertir entre entidad Publicacion y sus DTOs.
 */
@Component
public class PublicacionMapper {

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private ComentarioMapper comentarioMapper;

    /**
     * Convierte una entidad Publicacion a su DTO.
     *
     * @param publicacion la entidad Publicacion a convertir
     * @return el DTO correspondiente con autor, likes y comentarios
     */
    public PublicacionDTO toDTO(Publicacion publicacion) {
        PublicacionDTO dto = new PublicacionDTO();
        dto.setId(publicacion.getId());
        
        // Mapear autor a UsuarioDTO
        UsuarioDTO autorDTO = new UsuarioDTO();
        autorDTO.setUserId(publicacion.getAutor().getUserId());
        autorDTO.setEmail(publicacion.getAutor().getEmail());
        autorDTO.setGivenName(publicacion.getAutor().getGivenName());
        autorDTO.setProfileImageUrl(publicacion.getAutor().getProfileImageUrl());
        dto.setAutor(autorDTO); 
        
        dto.setImageUrl(publicacion.getImageUrl());
        dto.setTitulo(publicacion.getTitulo());
        dto.setComentario(publicacion.getComentario());
        dto.setPrivacidad(publicacion.getPrivacidad());
        dto.setCreatedAt(publicacion.getCreatedAt());

        // Mapear likes si existen
        List<LikeDTO> likeDTOs = (publicacion.getLikes() != null) 
            ? publicacion.getLikes().stream()
                .map(likeMapper::toDTO)
                .toList() 
            : new ArrayList<>();
        dto.setLikes(likeDTOs);

        // Mapear comentarios si existen
        if (publicacion.getComentarios() != null) {
            dto.setComentarios(publicacion.getComentarios().stream()
                .map(comentarioMapper::toDTO)
                .toList());
        }

        return dto;
    }

    /**
     * Convierte un DTO PublicacionDTO a la entidad Publicacion.
     *
     * @param dto el DTO con los datos para crear/actualizar la entidad
     * @return la entidad Publicacion correspondiente
     * @throws UsuarioNotFoundException si no se encuentra el usuario por userId
     */
    public Publicacion toEntity(PublicacionDTO dto) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(dto.getId());
    
        // Obtener el 'Usuario' a partir del 'userId' del DTO
        Usuario usuario = usuarioRepository.findByUserId(dto.getAutor().getUserId())
            .orElseThrow(() -> new UsuarioNotFoundException(dto.getAutor().getUserId()));  // Lanzar la excepción si no se encuentra el usuario
    
        publicacion.setAutor(usuario);  // Establecer el autor encontrado
    
        publicacion.setImageUrl(dto.getImageUrl());
        publicacion.setTitulo(dto.getTitulo());
        publicacion.setComentario(dto.getComentario());
        publicacion.setPrivacidad(dto.getPrivacidad());
        publicacion.setCreatedAt(dto.getCreatedAt());
    
        return publicacion;
    }
}