package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Ticket;

/**
 * Repositorio para la entidad {@link Ticket}.
 * Permite gestionar las operaciones CRUD y consultas personalizadas para los tickets.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>  {

    /**
     * Obtiene todos los tickets creados por un usuario específico.
     *
     * @param userId ID del usuario autor de los tickets.
     * @return Lista de tickets asociados al usuario.
     */
    List<Ticket> findByAutorUserId(String userId);

    /**
     * Obtiene todos los tickets creados por un usuario específico,
     * ordenados por fecha de creación en orden descendente (más recientes primero).
     *
     * @param userId ID del usuario autor de los tickets.
     * @return Lista de tickets ordenados por fecha de creación descendente.
     */
    List<Ticket> findByAutorUserIdOrderByFechaCreacionDesc(String userId);
    
}
