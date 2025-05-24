package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa una historia de un usuario")
public class HistoriaDTO {

    @Schema(description = "ID único de la historia", example = "123")
    private Long id;

    @Schema(description = "ID del usuario que creó la historia", example = "user123")
    private String userId;

    @Schema(description = "Fecha y hora de creación de la historia", example = "2025-05-23T12:34:56")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha y hora en la que la historia expira", example = "2025-05-30T12:34:56")
    private LocalDateTime expiraEn;

    @Schema(description = "Lista de imágenes asociadas a la historia")
    private List<HistoriaImagenDTO> imagenesUrls;

}
