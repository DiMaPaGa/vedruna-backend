package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Obtener todos los comentarios de una publicación

    List<Comentario> findAllByPublicacionId(Long publicacionId);

    // Obtener comentarios de una publicación ordenados por fecha de creación

    List<Comentario> findAllByPublicacionIdOrderByCreatedAtDesc(Long publicacionId);

    // Obtener todos los comentarios que son respuestas a otro comentario

    List<Comentario> findAllByComentarioPadreId(Long comentarioPadreId);
}