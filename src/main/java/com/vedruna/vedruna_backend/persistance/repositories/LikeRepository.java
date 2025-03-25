package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Like;
import com.vedruna.vedruna_backend.persistance.models.LikeId;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

      // Buscar los "likes" de una publicación específica
      List<Like> findByPublicacionId(Long publicacionId);

      // Buscar los "likes" de un usuario específico
      List<Like> findByLikeIdUserId(String userId);
  
      // Verificar si un usuario ha dado "like" a una publicación específica
      Optional<Like> findByLikeIdUserIdAndPublicacionId(String userId, Long publicacionId);
  
      // Contar la cantidad de "likes" de una publicación
      long countByPublicacionId(Long publicacionId);

    
}
