package com.vedruna.vedruna_backend.mappers;

import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.persistance.models.Ticket;

@Component
public class TicketMapper {

    // Convertir de Ticket a TicketDTO
    public TicketDTO ticketToTicketDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setUserId(ticket.getUserId());
        ticketDTO.setEquipoClase(ticket.getEquipoClase());
        ticketDTO.setTitulo(ticket.getTitulo());
        ticketDTO.setDescripcion(ticket.getDescripcion());
        ticketDTO.setFechaCreacion(ticket.getFechaCreacion());
        ticketDTO.setEstado(ticket.getEstado());
        
        return ticketDTO;
    }

    // Convertir de TicketDTO a Ticket
    public Ticket ticketDTOToTicket(TicketDTO ticketDTO) {
        if (ticketDTO == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setUserId(ticketDTO.getUserId());
        ticket.setEquipoClase(ticketDTO.getEquipoClase());
        ticket.setTitulo(ticketDTO.getTitulo());
        ticket.setDescripcion(ticketDTO.getDescripcion());
        ticket.setFechaCreacion(ticketDTO.getFechaCreacion());
        ticket.setEstado(ticketDTO.getEstado());

        return ticket;
    }
    
}
