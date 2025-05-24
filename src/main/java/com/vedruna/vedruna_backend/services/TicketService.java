package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.exceptions.TicketNotFoundException;

/**
 * Interfaz que define los métodos para gestionar los tickets.
 */
@Service
public interface TicketService {

    /**
     * Crea un nuevo ticket
     * @param ticketDTO Información del ticket
     * @return  Ticket creado
     */
    TicketDTO crearTicket(TicketDTO ticketDTO);

    /**
     * Actualiza un ticket existente
     * @param id ID del ticket
     * @param ticketDTO Información del ticket actualizado
     * @return  Ticket actualizado
     * @throws TicketNotFoundException si el ticket no existe
     */
    TicketDTO actualizarTicket(Long id, TicketDTO ticketDTO) throws TicketNotFoundException;

    /**
     * Obtiene un ticket por su ID
     * @param id ID del ticket
     * @return  Ticket encontrado
     * @throws TicketNotFoundException si el ticket no existe
     */
    TicketDTO obtenerTicket(Long id) throws TicketNotFoundException;

    /**
     * Obtiene todos los tickets de un usuario
     * @param userId ID del usuario
     * @return  Lista de tickets del usuario
     */
    List<TicketDTO> obtenerTicketsPorUsuario(String userId);

    /**
     * Obtiene todos los tickets de un usuario ordenados por fecha
     * @param userId ID del usuario
     * @return  Lista de tickets del usuario ordenados por fecha
     */
    List<TicketDTO> obtenerTicketsPorUsuarioOrdenadosPorFecha(String userId);
    
}
