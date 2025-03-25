package com.vedruna.vedruna_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioRequestDTO {
    private String userId;
    private Long publicacionId;
    private String comentario;
    private Long comentarioPadreId; // Opcional, si es una respuesta a otro comentario
}
