package com.vedruna.vedruna_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la solicitud de una notificación")
public class NotificacionRequest {

    @Schema(description = "ID del usuario que recibirá la notificación", example = "17895165461316161")
    private String userId;

    @Schema(description = "Mensaje de la notificación", example = "Tienes una nueva alerta")
    private String mensaje;
    
}
