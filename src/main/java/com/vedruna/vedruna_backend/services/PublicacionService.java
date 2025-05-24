package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.PublicacionDTO;
import com.vedruna.vedruna_backend.exceptions.ResourceNotFoundException;

/**
 * Interfaz que define los métodos para la gestión de publicaciones.
 */
@Service
public interface PublicacionService {

    /**
     * Crea una nueva publicación.
     * @param publicacionDTO datos de la publicación a crear
     * @return la publicación creada
     */
    PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    /**
     * Obtiene una publicación por su ID.
     * @param id ID de la publicación
     * @return la publicación encontrada
     * @throws ResourceNotFoundException si la publicación no se encuentra
     */
    PublicacionDTO obtenerPublicacionPorId(Long id) throws ResourceNotFoundException;

    /**
     * Obtiene todas las publicaciones de un usuario.
     * @param userId ID del usuario
     * @return  lista de publicaciones del usuario
     */
    List<PublicacionDTO> obtenerPublicacionesPorUsuario(String userId);

    /**
     * Obtiene todas las publicaciones que un usuario ha marcado con like.
     * @param userId ID del usuario
     * @return  lista de publicaciones con like del usuario
     */
    List<PublicacionDTO> obtenerPublicacionesConLikePorUsuario(String userId);

    /**
     * Obtiene todas las publicaciones accesibles para un usuario,
     * incluyendo públicas y privadas según permisos.
     *
     * @param userId ID del usuario.
     * @return Lista de todas las publicaciones accesibles.
     */
    List<PublicacionDTO> obtenerTodasLasPublicaciones(String userId);
}
