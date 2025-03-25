package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispositivoDTO {

    private Long id;
    private String userId; // Google UID
    private String expoPushToken;
    private LocalDateTime createdAt;
    
}
