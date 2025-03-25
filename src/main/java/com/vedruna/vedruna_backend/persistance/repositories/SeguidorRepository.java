package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Seguidor;
import com.vedruna.vedruna_backend.persistance.models.SeguidorId;

@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor, SeguidorId> {

    // Buscar todos los seguidores de un usuario
    List<Seguidor> findByIdSeguidoId(String seguidoId);

    // Buscar todos los usuarios seguidos por un usuario
    List<Seguidor> findByIdSeguidorId(String seguidorId);

    // Verificar si un usuario sigue a otro
    Optional<Seguidor> findByIdSeguidorIdAndIdSeguidoId(String seguidorId, String seguidoId);

    // Contar el número de seguidores de un usuario
    long countByIdSeguidoId(String seguidoId);

    // Contar el número de usuarios seguidos por un usuario
    long countByIdSeguidorId(String seguidorId);
    
}
