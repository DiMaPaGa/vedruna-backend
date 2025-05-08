package com.vedruna.vedruna_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDispositivoDTO {

    private String userId;
    private Long dispositivoId;
    
}
