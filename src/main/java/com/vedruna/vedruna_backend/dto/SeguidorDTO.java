package com.vedruna.vedruna_backend.dto;


import com.vedruna.vedruna_backend.persistance.models.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguidorDTO {

    private String seguidorId;
    private String seguidoId;
    private Estado estado;
    private String nombreSeguidor;
    private String imagenSeguidor;
}
