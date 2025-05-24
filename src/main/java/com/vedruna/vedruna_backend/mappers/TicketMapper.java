package com.vedruna.vedruna_backend.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.persistance.models.Ticket;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

/**
 * Mapper para convertir entre la entidad Ticket y su DTO correspondiente.
 */
@Component
public class TicketMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;  

    /**
     * Convierte una entidad Ticket a un TicketDTO.
     *
     * @param ticket la entidad Ticket a convertir
     * @return el DTO correspondiente, o null si el ticket es null
     */
    public TicketDTO ticketToTicketDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        
        // Mapeo del autor
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUserId(ticket.getAutor().getUserId());
        usuarioDTO.setEmail(ticket.getAutor().getEmail());
        usuarioDTO.setGivenName(ticket.getAutor().getGivenName());
        usuarioDTO.setProfileImageUrl(ticket.getAutor().getProfileImageUrl());
        ticketDTO.setAutor(usuarioDTO);

        ticketDTO.setEquipoClase(ticket.getEquipoClase());
        ticketDTO.setTitulo(ticket.getTitulo());
        ticketDTO.setDescripcion(ticket.getDescripcion());
        ticketDTO.setImageUrl(ticket.getImageUrl());
        ticketDTO.setFechaCreacion(ticket.getFechaCreacion());
        ticketDTO.setEstado(ticket.getEstado());
        
        return ticketDTO;
    }

    /**
     * Convierte un TicketDTO a la entidad Ticket.
     *
     * @param ticketDTO el DTO a convertir
     * @return la entidad Ticket creada a partir del DTO, o null si el DTO es null
     * @throws UsuarioNotFoundException si no se encuentra el usuario en la base de datos
     */
    public Ticket ticketDTOToTicket(TicketDTO ticketDTO) {
        if (ticketDTO == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());

        
        Usuario usuario = usuarioRepository.findByUserId(ticketDTO.getAutor().getUserId())
            .orElseThrow(() -> new UsuarioNotFoundException(ticketDTO.getAutor().getUserId())); 
        
        ticket.setAutor(usuario);  
        
        ticket.setEquipoClase(ticketDTO.getEquipoClase());
        ticket.setTitulo(ticketDTO.getTitulo());
        ticket.setDescripcion(ticketDTO.getDescripcion());
        ticket.setImageUrl(ticketDTO.getImageUrl());
        ticket.setFechaCreacion(ticketDTO.getFechaCreacion());
        ticket.setEstado(ticketDTO.getEstado());

        return ticket;
    }
}