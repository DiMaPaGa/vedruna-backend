package com.vedruna.vedruna_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaRequestDTO {
    private String usuarioId;  
    private List<HistoriaImagenDTO> imagenes;
}