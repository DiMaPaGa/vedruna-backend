package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.vedruna.vedruna_backend.persistance.models.Privacidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionDTO {

    private Long id;
    private UsuarioDTO autor;
    private String imageUrl;
    private String titulo;
    private String comentario;
    private Privacidad privacidad;
    private LocalDateTime createdAt;

    private List<LikeDTO> likes;
    private List<ComentarioDTO> comentarios;

    
}
