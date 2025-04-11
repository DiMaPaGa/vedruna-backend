package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Privacidad;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

     // Método para obtener todas las publicaciones ordenadas por fecha de creación (descendente)
     List<Publicacion> findAllByOrderByCreatedAtDesc();

     // Método para obtener todas las publicaciones por userId
     List<Publicacion> findByAutor_UserId(String userId);
 
     // Método para obtener todas las publicaciones que contienen un like de un usuario específico
     // Usamos un JOIN con la tabla de "likes" mediante la relación que tiene "Publicacion" con "Like"
     List<Publicacion> findByLikes_LikeId_UserId(String userId);

    // Método para obtener todas las publicaciones públicas
    List<Publicacion> findByPrivacidad(Privacidad privacidad);

    
}
