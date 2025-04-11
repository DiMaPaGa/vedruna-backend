package com.vedruna.vedruna_backend.mappers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.ComentarioDTO;
import com.vedruna.vedruna_backend.dto.ComentarioRequestDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.persistance.models.Comentario;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

@Component
public class ComentarioMapper {

    @Autowired
    private UsuarioRepository usuarioRepository; // ✅ Aquí inyectamos el repositorio

    public ComentarioDTO toDTO(Comentario comentario) {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(comentario.getId());
        dto.setUserId(comentario.getAutor().getUserId()); // Cambiado de comentario.getUserId()
        dto.setPublicacionId(comentario.getPublicacion().getId());
        dto.setComentario(comentario.getComentario());
        dto.setComentarioPadreId(comentario.getComentarioPadre() != null ? comentario.getComentarioPadre().getId() : null);
        dto.setCreatedAt(comentario.getCreatedAt());

        // Asignar nombre y URL de la foto del autor
        dto.setAutorName(comentario.getAutor().getGivenName());
        dto.setAutorProfileImageUrl(comentario.getAutor().getProfileImageUrl());

        if (comentario.getRespuestas() != null) {
            dto.setRespuestas(comentario.getRespuestas().stream().map(this::toDTO).toList());
        }

        return dto;
    }

    public Comentario toEntity(ComentarioRequestDTO dto) {
    Comentario comentario = new Comentario();
    
    // Buscar al usuario con ese userId
    Usuario usuario = usuarioRepository.findByUserId(dto.getUserId())
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con userId: " + dto.getUserId()));
    
    comentario.setAutor(usuario);  // Establecer el autor en el comentario
    comentario.setComentario(dto.getComentario());
    return comentario;
}
    
}
