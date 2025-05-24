package com.vedruna.vedruna_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa un dispositivo para notificaciones push")
public class DispositivoDTO {

    @Schema(description = "ID utilizado para notificaciones push con Expo", example = "ExponentPushToken[xxxxxxxxxxxxxx]", required = true)
    private String expoPushId;
    
}
