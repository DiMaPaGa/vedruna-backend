package com.vedruna.vedruna_backend.services;

import java.util.List;

import com.vedruna.vedruna_backend.dto.UsuarioDispositivoDTO;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;

/**
 * Interfaz de servicio para la gestión de relaciones entre usuarios y dispositivos.
 */
public interface UsuarioDispositivoService {

    /**
     * Guarda una nueva relación entre un usuario y un dispositivo.
     * 
     * @param usuario El usuario asociado a la relación.
     * @param dispositivo El dispositivo asociado a la relación.
     */
    void guardarRelacionUsuarioDispositivo(Usuario usuario, Dispositivo dispositivo);

    /**
     * Obtiene la asociación entre un usuario y un dispositivo por sus IDs.
     * 
     * @param usuarioId El ID del usuario.
     * @param dispositivoId El ID del dispositivo.
     * @return La asociación entre el usuario y el dispositivo en formato de DTO.
     */
    UsuarioDispositivoDTO obtenerRelacionUsuarioDispositivo(Long usuarioId, Long dispositivoId);

    /**
     * Obtiene los tokens de los dispositivos asociados a un usuario.
     * 
     * @param userId El ID del usuario.
     * @return Una lista de tokens de los dispositivos asociados al usuario.
     */
    List<String> obtenerTokensPorUserId(String userId);
}