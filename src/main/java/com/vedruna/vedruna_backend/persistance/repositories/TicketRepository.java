package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>  {

    // Buscar todos los tickets de un usuario específico
    List<Ticket> findByAutorUserId(String userId);

    // Buscar todos los tickets de un usuario específico, ordenados por fecha de creación descendente
    List<Ticket> findByAutorUserIdOrderByFechaCreacionDesc(String userId);
    
}
