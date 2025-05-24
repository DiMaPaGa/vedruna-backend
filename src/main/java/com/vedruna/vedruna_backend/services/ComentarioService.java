package com.vedruna.vedruna_backend.services;

import java.util.List;
import java.util.Optional;

import com.vedruna.vedruna_backend.dto.ComentarioDTO;
import com.vedruna.vedruna_backend.dto.ComentarioRequestDTO;

/**
 * Servicio para la gestión de comentarios.
 * Proporciona métodos para obtener, guardar y eliminar comentarios y sus respuestas.
 */
public interface ComentarioService {

    /**
     * Obtiene todos los comentarios asociados a una publicación específica.
     *
     * @param publicacionId Identificador de la publicación.
     * @return Lista de DTOs de comentarios relacionados con la publicación.
     */
    List<ComentarioDTO> obtenerComentariosPorPublicacion(Long publicacionId);

    /**
     * Obtiene todos los comentarios de una publicación, ordenados por fecha de creación descendente.
     *
     * @param publicacionId Identificador de la publicación.
     * @return Lista de DTOs de comentarios ordenados por fecha de creación.
     */
    List<ComentarioDTO> obtenerComentariosPorPublicacionOrdenados(Long publicacionId);

    /**
     * Obtiene todas las respuestas a un comentario específico.
     *
     * @param comentarioPadreId Identificador del comentario padre.
     * @return Lista de DTOs de comentarios que son respuestas.
     */
    List<ComentarioDTO> obtenerRespuestas(Long comentarioPadreId);

    /**
     * Obtiene un comentario por su identificador.
     *
     * @param id Identificador del comentario.
     * @return Optional con el DTO del comentario si existe, o vacío si no.
     */
    Optional<ComentarioDTO> obtenerPorId(Long id);

    /**
     * Guarda un nuevo comentario basado en un DTO de solicitud.
     *
     * @param comentarioDTO DTO con los datos del comentario a guardar.
     * @return DTO del comentario guardado.
     */
    ComentarioDTO guardarComentario(ComentarioRequestDTO comentarioDTO);

    /**
     * Elimina un comentario por su identificador.
     *
     * @param id Identificador del comentario a eliminar.
     */
    void eliminarComentario(Long id);
    
}
