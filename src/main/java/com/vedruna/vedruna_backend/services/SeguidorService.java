package com.vedruna.vedruna_backend.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.persistance.models.Estado;

@Service
public interface SeguidorService {
    // Obtener los seguidores de un usuario con estado específico, paginados
    Page<SeguidorDTO> obtenerSeguidores(String seguidoId, Estado estado, Pageable pageable);

    // Obtener los usuarios seguidos por un usuario con estado específico, paginados
    Page<SeguidorDTO> obtenerSeguidos(String seguidorId, Estado estado, Pageable pageable);

    // Verificar si un usuario sigue a otro
    boolean esSeguidor(String seguidorId, String seguidoId);

    // Contar el número de seguidores de un usuario con estado específico
    long contarSeguidores(String seguidoId, Estado estado);

    // Contar el número de usuarios seguidos por un usuario con estado específico
    long contarSeguidos(String seguidorId, Estado estado);

    // Seguir a un usuario
    void seguirUsuario(String seguidorId, String seguidoId);

    // Eliminar un seguimiento
    void dejarDeSeguir(String seguidorId, String seguidoId);

    // Aceptar una solicitud de seguimiento
    void aceptarSolicitud(String seguidorId, String seguidoId);
    
}
