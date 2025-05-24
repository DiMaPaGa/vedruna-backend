package com.vedruna.vedruna_backend.persistance.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Estado;
import com.vedruna.vedruna_backend.persistance.models.Seguidor;
import com.vedruna.vedruna_backend.persistance.models.SeguidorId;

/**
 * Repositorio para la entidad {@link Seguidor}.
 * Permite gestionar las relaciones de seguimiento entre usuarios,
 * con soporte para estados (pendiente, aceptado) y paginación.
 */
@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor, SeguidorId> {

    /**
     * Obtiene una página de seguidores de un usuario específico filtrados por estado.
     *
     * @param seguidoId ID del usuario que es seguido.
     * @param estado Estado del seguimiento (por ejemplo, PENDIENTE o ACEPTADO).
     * @param pageable Información de paginación.
     * @return Página con los seguidores que cumplen los criterios.
     */
    Page<Seguidor> findByIdSeguidoIdAndEstado(String seguidoId, Estado estado, Pageable pageable);

    /**
     * Obtiene una página de usuarios que sigue un usuario específico, filtrados por estado.
     *
     * @param seguidorId ID del usuario que sigue.
     * @param estado Estado del seguimiento.
     * @param pageable Información de paginación.
     * @return Página con los usuarios seguidos que cumplen los criterios.
     */
    Page<Seguidor> findByIdSeguidorIdAndEstado(String seguidorId, Estado estado, Pageable pageable);

    /**
     * Busca la relación de seguimiento entre dos usuarios específicos.
     *
     * @param seguidorId ID del usuario que sigue.
     * @param seguidoId ID del usuario que es seguido.
     * @return Optional con la relación de seguimiento si existe.
     */
    Optional<Seguidor> findByIdSeguidorIdAndIdSeguidoId(String seguidorId, String seguidoId);

    /**
     * Verifica si un usuario sigue a otro.
     *
     * @param seguidorId ID del usuario que sigue.
     * @param seguidoId ID del usuario seguido.
     * @return true si existe la relación de seguimiento, false en caso contrario.
     */
    boolean existsByIdSeguidorIdAndIdSeguidoId(String seguidorId, String seguidoId);

    /**
     * Elimina la relación de seguimiento entre dos usuarios.
     *
     * @param seguidorId ID del usuario que sigue.
     * @param seguidoId ID del usuario seguido.
     */
    void deleteByIdSeguidorIdAndIdSeguidoId(String seguidorId, String seguidoId);

    /**
     * Cuenta cuántos usuarios sigue un usuario específico con un estado determinado.
     *
     * @param seguidorId ID del usuario que sigue.
     * @param estado Estado del seguimiento.
     * @return Número de usuarios que sigue.
     */
    long countByIdSeguidorIdAndEstado(String seguidorId, Estado estado);

    /**
     * Cuenta cuántos seguidores tiene un usuario específico con un estado determinado.
     *
     * @param seguidoId ID del usuario que es seguido.
     * @param estado Estado del seguimiento.
     * @return Número de seguidores.
     */
    long countByIdSeguidoIdAndEstado(String seguidoId, Estado estado);
}
