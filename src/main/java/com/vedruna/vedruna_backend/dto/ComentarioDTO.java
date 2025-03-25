package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private Long id;
    private String userId;
    private Long publicacionId; // Solo pasamos el ID en lugar de la entidad completa
    private String comentario;
    private Long comentarioPadreId; // Para comentarios anidados
    private LocalDateTime createdAt;
    private List<ComentarioDTO> respuestas; // Para respuestas anidadas
    
}
