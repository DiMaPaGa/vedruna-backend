package com.vedruna.vedruna_backend.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear o actualizar una historia")
public class HistoriaRequestDTO {

    @Schema(description = "ID del usuario propietario de la historia", example = "155484162848480")
    private String usuarioId;

    @Schema(description = "Lista de imágenes que componen la historia")
    private List<HistoriaImagenDTO> imagenes;

}