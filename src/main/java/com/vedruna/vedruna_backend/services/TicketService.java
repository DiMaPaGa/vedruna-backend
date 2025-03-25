package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.exceptions.TicketNotFoundException;

@Service
public interface TicketService {
    TicketDTO crearTicket(TicketDTO ticketDTO);
    TicketDTO actualizarTicket(Long id, TicketDTO ticketDTO) throws TicketNotFoundException;
    TicketDTO obtenerTicket(Long id) throws TicketNotFoundException;
    List<TicketDTO> obtenerTicketsPorUsuario(String userId);
    List<TicketDTO> obtenerTicketsPorUsuarioOrdenadosPorFecha(String userId);
    
}
