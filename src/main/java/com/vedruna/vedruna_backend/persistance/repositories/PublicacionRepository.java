package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Privacidad;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;

/**
 * Repositorio para la entidad {@link Publicacion}.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas
 * sobre publicaciones, incluyendo filtrados por autor, privacidad y "likes".
 */
@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

     /**
     * Obtiene todas las publicaciones ordenadas por fecha de creación en orden descendente.
     *
     * @return Lista de publicaciones ordenadas de la más reciente a la más antigua.
     */
     List<Publicacion> findAllByOrderByCreatedAtDesc();

     /**
     * Obtiene todas las publicaciones realizadas por un usuario específico.
     *
     * @param userId ID del usuario autor.
     * @return Lista de publicaciones creadas por el usuario.
     */
     List<Publicacion> findByAutor_UserId(String userId);
 
     /**
     * Obtiene todas las publicaciones que contienen al menos un "like" dado por un usuario específico.
     * Realiza una consulta que une las publicaciones con sus "likes".
     *
     * @param userId ID del usuario que dio el "like".
     * @return Lista de publicaciones que han recibido "like" del usuario.
     */
     List<Publicacion> findByLikes_LikeId_UserId(String userId);

    /**
     * Obtiene todas las publicaciones filtradas por nivel de privacidad.
     *
     * @param privacidad Enum {@link Privacidad} que indica el nivel de privacidad (PÚBLICA o PRIVADA).
     * @return Lista de publicaciones que coinciden con el nivel de privacidad dado.
     */
    List<Publicacion> findByPrivacidad(Privacidad privacidad);
    
}
