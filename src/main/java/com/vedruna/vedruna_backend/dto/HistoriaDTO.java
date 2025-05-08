package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaDTO {

    private Long id;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime expiraEn;
    private List<HistoriaImagenDTO> imagenesUrls;
}
