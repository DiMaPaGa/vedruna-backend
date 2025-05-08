package com.vedruna.vedruna_backend.services;


import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;

public interface DispositivoService {

    Dispositivo obtenerPorId(Long id); 
    DispositivoDTO obtenerODarDeAlta(String expoPushId, String userId);   
}
