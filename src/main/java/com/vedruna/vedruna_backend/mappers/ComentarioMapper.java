package com.vedruna.vedruna_backend.mappers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.ComentarioDTO;
import com.vedruna.vedruna_backend.dto.ComentarioRequestDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.persistance.models.Comentario;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

/**
 * Clase encargada de convertir entre entidades Comentario y sus DTOs correspondientes.
 */
@Component
public class ComentarioMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Convierte una entidad Comentario a un ComentarioDTO.
     * 
     * @param comentario entidad Comentario a convertir
     * @return ComentarioDTO con datos mapeados desde la entidad
     */
    public ComentarioDTO toDTO(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(comentario.getId());
        dto.setUserId(comentario.getAutor().getUserId()); 
        dto.setPublicacionId(comentario.getPublicacion().getId());
        dto.setComentario(comentario.getComentario());
        dto.setComentarioPadreId(comentario.getComentarioPadre() != null ? comentario.getComentarioPadre().getId() : null);
        dto.setCreatedAt(comentario.getCreatedAt());

        // Añadir información adicional del autor para el DTO
        dto.setAutorName(comentario.getAutor().getGivenName());
        dto.setAutorProfileImageUrl(comentario.getAutor().getProfileImageUrl());

        // Convertir recursivamente las respuestas del comentario, si existen
        if (comentario.getRespuestas() != null) {
            dto.setRespuestas(comentario.getRespuestas().stream().map(this::toDTO).toList());
        }

        return dto;
    }

     /**
     * Convierte un ComentarioRequestDTO a una entidad Comentario.
     * Busca el usuario asociado mediante userId y establece el autor.
     * 
     * @param dto DTO con los datos para crear el comentario
     * @return entidad Comentario con los datos asignados
     * @throws UsuarioNotFoundException si no se encuentra el usuario con el userId dado
     */
    public Comentario toEntity(ComentarioRequestDTO dto) {
    Comentario comentario = new Comentario();
    
    // Buscar al usuario con ese userId, lanzar excepción si no existe
    Usuario usuario = usuarioRepository.findByUserId(dto.getUserId())
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con userId: " + dto.getUserId()));
    
    comentario.setAutor(usuario);  // Establecer el autor en el comentario
    comentario.setComentario(dto.getComentario());
    return comentario;
}
    
}
