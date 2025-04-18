package com.vedruna.vedruna_backend.services;

import java.util.List;
import java.util.Optional;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;

public interface DispositivoService {

    DispositivoDTO guardarDispositivo(DispositivoDTO dispositivoDTO);
    List<DispositivoDTO> obtenerDispositivosPorUserId(String userId);
    Optional<DispositivoDTO> obtenerDispositivoPorId(String expoPushId);
    
}
