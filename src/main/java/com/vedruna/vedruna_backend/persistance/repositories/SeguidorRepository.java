package com.vedruna.vedruna_backend.persistance.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Estado;
import com.vedruna.vedruna_backend.persistance.models.Seguidor;
import com.vedruna.vedruna_backend.persistance.models.SeguidorId;

@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor, SeguidorId> {

    // Buscar todos los seguidores de un usuario con estado específico y paginados
    Page<Seguidor> findByIdSeguidoIdAndEstado(String seguidoId, Estado estado, Pageable pageable);

    // Buscar todos los usuarios seguidos por un usuario con estado específico y paginados
    Page<Seguidor> findByIdSeguidorIdAndEstado(String seguidorId, Estado estado, Pageable pageable);

    // Verificar si un usuario sigue a otro
    Optional<Seguidor> findByIdSeguidorIdAndIdSeguidoId(String seguidorId, String seguidoId);

    boolean existsByIdSeguidorIdAndIdSeguidoId(String seguidorId, String seguidoId);

    void deleteByIdSeguidorIdAndIdSeguidoId(String seguidorId, String seguidoId);

    // Contar el número de usuarios que sigue un usuario con estado específico
    long countByIdSeguidorIdAndEstado(String seguidorId, Estado estado);

    // Contar el número de seguidores de un usuario con estado específico
    long countByIdSeguidoIdAndEstado(String seguidoId, Estado estado);
}
