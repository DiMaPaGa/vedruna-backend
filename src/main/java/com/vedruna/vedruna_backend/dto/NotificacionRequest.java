package com.vedruna.vedruna_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequest {

    private String userId;
    private String mensaje;
    
}
