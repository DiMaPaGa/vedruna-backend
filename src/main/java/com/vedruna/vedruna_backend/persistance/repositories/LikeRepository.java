package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Like;
import com.vedruna.vedruna_backend.persistance.models.LikeId;

/**
 * Repositorio para la entidad {@link Like}, que representa los "likes" dados por usuarios a publicaciones.
 * Permite operaciones CRUD y consultas específicas relacionadas con los "likes".
 *
 * Extiende {@link JpaRepository} con clave primaria compuesta {@link LikeId}.
 */
@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

      /**
     * Obtiene todos los "likes" asociados a una publicación específica.
     *
     * @param publicacionId ID de la publicación.
     * @return Lista de objetos {@link Like} que corresponden a los "likes" de la publicación.
     */
      List<Like> findByPublicacionId(Long publicacionId);

     /**
     * Obtiene todos los "likes" realizados por un usuario específico.
     *
     * @param userId ID único del usuario.
     * @return Lista de objetos {@link Like} que corresponden a los "likes" dados por el usuario.
     */
      List<Like> findByLikeIdUserId(String userId);
  
     /**
     * Verifica si un usuario ha dado "like" a una publicación específica.
     *
     * @param userId       ID del usuario.
     * @param publicacionId ID de la publicación.
     * @return {@link Optional} que contiene el {@link Like} si existe, o vacío si no.
     */
      Optional<Like> findByLikeIdUserIdAndPublicacionId(String userId, Long publicacionId);
  
     /**
     * Cuenta la cantidad total de "likes" que tiene una publicación.
     *
     * @param publicacionId ID de la publicación.
     * @return Número de "likes" asociados a la publicación.
     */
      long countByPublicacionId(Long publicacionId);

    
}
