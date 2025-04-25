package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByUserId(String userId);

    @Query("SELECT u FROM Usuario u WHERE u.userId != :userId AND u.userId NOT IN (" +
       "SELECT s.id.seguidoId FROM Seguidor s WHERE s.id.seguidorId = :userId AND s.estado = com.vedruna.vedruna_backend.persistance.models.Estado.ACEPTADO)")
    Page<Usuario> encontrarUsuariosNoSeguidos(@Param("userId") String userId, Pageable pageable);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.givenName) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Usuario> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}
