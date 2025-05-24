package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Comentario;

/**
 * Repositorio para la entidad {@link Comentario}.
 * Proporciona operaciones CRUD y consultas personalizadas relacionadas con los comentarios.
 * 
 * <p>Extiende {@link JpaRepository}, lo que permite usar métodos estándar como 
 * {@code findAll()}, {@code findById()}, {@code save()}, {@code delete()}, etc.</p>
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    /**
     * Obtiene todos los comentarios asociados a una publicación específica.
     *
     * @param publicacionId ID de la publicación.
     * @return Lista de comentarios asociados a la publicación.
     */
    List<Comentario> findAllByPublicacionId(Long publicacionId);

    /**
     * Obtiene todos los comentarios de una publicación ordenados por fecha de creación 
     * de forma descendente (los más recientes primero).
     *
     * @param publicacionId ID de la publicación.
     * @return Lista de comentarios ordenados por {@code createdAt} descendente.
     */
    List<Comentario> findAllByPublicacionIdOrderByCreatedAtDesc(Long publicacionId);

    /**
     * Obtiene todos los comentarios que son respuestas a un comentario padre específico.
     * 
     * @param comentarioPadreId ID del comentario padre.
     * @return Lista de comentarios que son respuestas al comentario especificado.
     */
    List<Comentario> findAllByComentarioPadreId(Long comentarioPadreId);
}