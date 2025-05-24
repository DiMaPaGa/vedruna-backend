package com.vedruna.vedruna_backend.services;


import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;

import jakarta.persistence.EntityNotFoundException;

/**
 * Servicio para la gestión de dispositivos asociados a los usuarios.
 */
public interface DispositivoService {

     /**
     * Obtiene un dispositivo por su identificador único.
     *
     * @param id el identificador del dispositivo.
     * @return el dispositivo correspondiente al ID proporcionado.
     * @throws EntityNotFoundException si no se encuentra el dispositivo con el ID dado.
     */
    Dispositivo obtenerPorId(Long id); 

    /**
     * Obtiene un dispositivo por su Expo Push ID o, si no existe, lo crea y lo asocia al usuario dado.
     *
     * @param expoPushId el identificador único de Expo Push del dispositivo.
     * @param userId el identificador único del usuario al que se asociará el dispositivo.
     * @return un DTO del dispositivo obtenido o creado.
     */
    DispositivoDTO obtenerODarDeAlta(String expoPushId, String userId);   
}
