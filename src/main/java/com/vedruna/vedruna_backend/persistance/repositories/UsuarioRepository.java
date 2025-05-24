package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Usuario;

/**
 * Repositorio para la entidad {@link Usuario}.
 * Proporciona métodos para realizar consultas específicas sobre usuarios,
 * incluyendo búsquedas por userId, usuarios no seguidos y búsquedas por nombre.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Busca un usuario por su userId único.
     *
     * @param userId Identificador único del usuario.
     * @return Optional con el usuario encontrado, o vacío si no existe.
     */
    Optional<Usuario> findByUserId(String userId);

    /**
     * Encuentra usuarios que no están siendo seguidos por el usuario especificado y que no son el mismo usuario.
     *
     * @param userId   UserId del usuario que realiza la consulta.
     * @param pageable Parámetros de paginación.
     * @return Página con usuarios que no son seguidos por el usuario dado.
     */
    @Query("SELECT u FROM Usuario u WHERE u.userId != :userId AND u.userId NOT IN (" +
       "SELECT s.id.seguidoId FROM Seguidor s WHERE s.id.seguidorId = :userId AND s.estado = com.vedruna.vedruna_backend.persistance.models.Estado.ACEPTADO)")
    Page<Usuario> encontrarUsuariosNoSeguidos(@Param("userId") String userId, Pageable pageable);

    /**
     * Busca usuarios cuyo nombre (givenName) contenga el texto especificado, sin importar mayúsculas o minúsculas.
     *
     * @param nombre   Fragmento de nombre a buscar.
     * @param pageable Parámetros de paginación.
     * @return Página con usuarios que coinciden con la búsqueda por nombre.
     */
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.givenName) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Usuario> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
