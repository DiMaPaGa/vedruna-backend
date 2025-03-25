package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaDTO {

    private Long id;
    private String userId;
    private String imageUrl;
    private String texto;
    private LocalDateTime createdAt;
    private LocalDateTime expiraEn;

}
