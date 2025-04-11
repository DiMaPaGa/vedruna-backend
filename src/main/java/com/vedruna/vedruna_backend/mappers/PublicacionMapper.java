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


@Component
public class PublicacionMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;  // Inyectar el repositorio de Usuario

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private ComentarioMapper comentarioMapper;

    public PublicacionDTO toDTO(Publicacion publicacion) {
        PublicacionDTO dto = new PublicacionDTO();
        dto.setId(publicacion.getId());
        
        // Mapeo del autor (usuario) de la publicación desde la entidad
        UsuarioDTO autorDTO = new UsuarioDTO();
        autorDTO.setUserId(publicacion.getAutor().getUserId());  // Obtención directa del autor
        autorDTO.setEmail(publicacion.getAutor().getEmail());
        autorDTO.setGivenName(publicacion.getAutor().getGivenName());
        autorDTO.setProfileImageUrl(publicacion.getAutor().getProfileImageUrl());
        dto.setAutor(autorDTO); // Establecer el autor DTO en la publicación DTO
        
        dto.setImageUrl(publicacion.getImageUrl());
        dto.setTitulo(publicacion.getTitulo());
        dto.setComentario(publicacion.getComentario());
        dto.setPrivacidad(publicacion.getPrivacidad());
        dto.setCreatedAt(publicacion.getCreatedAt());

        // Mapeo de los likes
        List<LikeDTO> likeDTOs = (publicacion.getLikes() != null) 
            ? publicacion.getLikes().stream()
                .map(likeMapper::toDTO)
                .toList() 
            : new ArrayList<>();
        dto.setLikes(likeDTOs);

        // Mapeo de los comentarios
        if (publicacion.getComentarios() != null) {
            dto.setComentarios(publicacion.getComentarios().stream()
                .map(comentarioMapper::toDTO)
                .toList());
        }

        return dto;
    }

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