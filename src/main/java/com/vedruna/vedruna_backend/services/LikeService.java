package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.LikeRequestDTO;
import com.vedruna.vedruna_backend.persistance.models.Like;

/**
 * Interfaz para el servicio de gestión de likes.
 * Proporciona métodos para obtener, crear, eliminar y consultar likes en publicaciones.
 */
@Service
public interface LikeService {

    /**
     * Obtiene un like existente o crea uno nuevo para un usuario y publicación dados.
     *
     * @param userId El identificador único del usuario que da el like.
     * @param publicacionId El identificador único de la publicación que recibe el like.
     * @return El objeto Like correspondiente.
     */
    Like obtenerOCrearLike(String userId, Long publicacionId);

    /**
     * Crea un nuevo like para un usuario y publicación dados.
     *
     * @param requestDTO El objeto LikeRequestDTO que contiene los datos del like.
     * @return El objeto LikeDTO correspondiente.
     */
    LikeDTO darLike(LikeRequestDTO requestDTO);

    /**
     * Elimina un like existente para un usuario y publicación dados.
     *
     * @param userId El identificador único del usuario que da el like.
     * @param publicacionId El identificador único de la publicación que recibe el like.
     */
    void quitarLike(String userId, Long publicacionId);

    /**
     * Obtiene todos los likes de una publicación.
     *
     * @param publicacionId El identificador único de la publicación.
     * @return Una lista de objetos LikeDTO que representan los likes de la publicación.
     */
    List<LikeDTO> obtenerLikesDePublicacion(Long publicacionId);

    /**
     * Verifica si un usuario ha dado like a una publicación.
     *
     * @param userId El identificador único del usuario.
     * @param publicacionId El identificador único de la publicación.
     * @return Verdadero si el usuario ha dado like, falso en caso contrario.
     */
    boolean usuarioHaDadoLike(String userId, Long publicacionId);

    /**
     * Obtiene el total de likes de una publicación.
     *
     * @param publicacionId El identificador único de la publicación.
     * @return El total de likes de la publicación.
     */
    long contarLikesDePublicacion(Long publicacionId);
}
