package com.vedruna.vedruna_backend.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.persistance.models.Estado;

/**
 * Servicio para gestionar las relaciones de seguimiento entre usuarios.
 */
@Service
public interface SeguidorService {
    
    /**
     * Obtiene los seguidores de un usuario con un estado específico, paginados.
     *
     * @param seguidoId ID del usuario seguido.
     * @param estado Estado del seguimiento (ej. ACEPTADO, PENDIENTE).
     * @param pageable Información de paginación.
     * @return Página con los DTOs de seguidores.
     */
    Page<SeguidorDTO> obtenerSeguidores(String seguidoId, Estado estado, Pageable pageable);

    /**
     * Obtiene los usuarios seguidos por un usuario con un estado específico, paginados.
     *
     * @param seguidorId ID del usuario seguidor.
     * @param estado Estado del seguimiento.
     * @param pageable Información de paginación.
     * @return Página con los DTOs de usuarios seguidos.
     */
    Page<SeguidorDTO> obtenerSeguidos(String seguidorId, Estado estado, Pageable pageable);

    /**
     * Verifica si un usuario sigue a otro.
     *
     * @param seguidorId ID del usuario seguidor.
     * @param seguidoId ID del usuario seguido.
     * @return true si el seguidor sigue al seguido, false en caso contrario.
     */
    boolean esSeguidor(String seguidorId, String seguidoId);

    /**
     * Cuenta el número de seguidores de un usuario con un estado específico.
     *
     * @param seguidoId ID del usuario seguido.
     * @param estado Estado del seguimiento.
     * @return Número de seguidores.
     */
    long contarSeguidores(String seguidoId, Estado estado);

    /**
     * Cuenta el número de usuarios seguidos por un usuario con un estado específico.
     *
     * @param seguidorId ID del usuario seguidor.
     * @param estado Estado del seguimiento.
     * @return Número de usuarios seguidos.
     */
    long contarSeguidos(String seguidorId, Estado estado);

    /**
     * Inicia el seguimiento de un usuario.
     *
     * @param seguidorId ID del usuario que va a seguir.
     * @param seguidoId ID del usuario a seguir.
     */
    void seguirUsuario(String seguidorId, String seguidoId);

    /**
     * Elimina el seguimiento de un usuario.
     *
     * @param seguidorId ID del usuario que deja de seguir.
     * @param seguidoId ID del usuario dejado de seguir.
     */
    void dejarDeSeguir(String seguidorId, String seguidoId);

    /**
     * Acepta una solicitud de seguimiento.
     *
     * @param seguidorId ID del usuario que solicitó seguir.
     * @param seguidoId ID del usuario que acepta la solicitud.
     */
    void aceptarSolicitud(String seguidorId, String seguidoId);
    
}
