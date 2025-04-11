package com.vedruna.vedruna_backend.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.persistance.models.Ticket;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

@Component
public class TicketMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;  // Repositorio para obtener al usuario

    // Convertir de Ticket a TicketDTO
    public TicketDTO ticketToTicketDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        
        // Mapear el usuario
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUserId(ticket.getAutor().getUserId());
        usuarioDTO.setEmail(ticket.getAutor().getEmail());
        usuarioDTO.setGivenName(ticket.getAutor().getGivenName());
        usuarioDTO.setProfileImageUrl(ticket.getAutor().getProfileImageUrl());
        ticketDTO.setAutor(usuarioDTO);

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

        // Obtener el 'Usuario' a partir del 'userId' del DTO
        Usuario usuario = usuarioRepository.findByUserId(ticketDTO.getAutor().getUserId())
            .orElseThrow(() -> new UsuarioNotFoundException(ticketDTO.getAutor().getUserId())); // Aquí puedes lanzar tu propia excepción si lo deseas
        
        ticket.setAutor(usuario);  // Establecer el autor encontrado
        
        ticket.setEquipoClase(ticketDTO.getEquipoClase());
        ticket.setTitulo(ticketDTO.getTitulo());
        ticket.setDescripcion(ticketDTO.getDescripcion());
        ticket.setFechaCreacion(ticketDTO.getFechaCreacion());
        ticket.setEstado(ticketDTO.getEstado());

        return ticket;
    }
}