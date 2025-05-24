package com.vedruna.vedruna_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa una imagen dentro de una historia")
public class HistoriaImagenDTO {

    @Schema(description = "URL de la imagen", example = "https://example.com/imagen.jpg")
    private String imagenUrl;

    @Schema(description = "Texto o descripción de la imagen", example = "Graduaciones en Vedruna")
    private String texto;

    @Schema(description = "Orden de aparición de la imagen dentro de la historia", example = "1")
    private Integer orden;

}