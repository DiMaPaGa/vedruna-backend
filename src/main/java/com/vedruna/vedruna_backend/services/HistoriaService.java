package com.vedruna.vedruna_backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.persistance.models.Historia;

public interface HistoriaService {
    HistoriaDTO guardarHistoria(HistoriaDTO historiaDTO);
    List<HistoriaDTO> obtenerHistoriasPorUserId(String userId);
    List<HistoriaDTO> obtenerHistoriasNoExpiradas(LocalDateTime currentDate);
    Optional<HistoriaDTO> obtenerHistoriaPorId(Long id);
    void eliminarHistoria(Long id);
}
