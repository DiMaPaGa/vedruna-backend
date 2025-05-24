package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.vedruna.vedruna_backend.persistance.models.Privacidad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa una publicación")
public class PublicacionDTO {

     @Schema(description = "Identificador único de la publicación", example = "123")
    private Long id;

    @Schema(description = "Datos del autor de la publicación")
    private UsuarioDTO autor;

    @Schema(description = "URL de la imagen de la publicación", example = "https://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "Título de la publicación", example = "Entrega de títulos")
    private String titulo;

    @Schema(description = "Comentario o texto principal de la publicación", example = "¡Enhorabuena!")
    private String comentario;

    @Schema(description = "Privacidad de la publicación (ej. PÚBLICA, PRIVADA)")
    private Privacidad privacidad;

    @Schema(description = "Fecha y hora de creación de la publicación")
    private LocalDateTime createdAt;

    @Schema(description = "Lista de likes asociados a la publicación")
    private List<LikeDTO> likes;

    @Schema(description = "Lista de comentarios asociados a la publicación")
    private List<ComentarioDTO> comentarios;

    
}
