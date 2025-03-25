package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguidorDTO {

    private String seguidorId;
    private String seguidoId;
    private LocalDateTime createdAt;
}
