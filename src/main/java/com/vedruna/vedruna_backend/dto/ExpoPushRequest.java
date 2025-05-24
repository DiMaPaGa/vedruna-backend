package com.vedruna.vedruna_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Request para asociar un dispositivo a un usuario")
public class ExpoPushRequest {

    @Schema(description = "ID utilizado para notificaciones push con Expo", example = "ExponentPushToken[xxxxxxxxxxxxxx]", required = true)
    private String expoPushId;

    @Schema(description = "ID del usuario asociado al dispositivo", example = "15748484848484", required = true)
    private String userId;
    
}
