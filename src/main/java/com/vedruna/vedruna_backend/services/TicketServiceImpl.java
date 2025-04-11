package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.exceptions.TicketNotFoundException;
import com.vedruna.vedruna_backend.mappers.TicketMapper;
import com.vedruna.vedruna_backend.persistance.models.Ticket;
import com.vedruna.vedruna_backend.persistance.repositories.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UsuarioService usuarioService;  // Verificar que el usuario existe

    @Transactional
    @Override
    public TicketDTO crearTicket(TicketDTO ticketDTO) {
        
        // Convertir el DTO a la entidad Ticket
        Ticket ticket = ticketMapper.ticketDTOToTicket(ticketDTO);
        ticket = ticketRepository.save(ticket); // Guardar en la base de datos
        return ticketMapper.ticketToTicketDTO(ticket); // Convertir nuevamente a DTO y devolverlo
    }

    @Transactional
    @Override
    public TicketDTO actualizarTicket(Long id, TicketDTO ticketDTO) throws TicketNotFoundException {
        // Verificar si el ticket existe
        Ticket ticket = obtenerTicketEntity(id);
        ticket.setTitulo(ticketDTO.getTitulo());
        ticket.setDescripcion(ticketDTO.getDescripcion());
        ticket.setEquipoClase(ticketDTO.getEquipoClase());
        ticket.setEstado(ticketDTO.getEstado());
        ticket = ticketRepository.save(ticket); // Guardar el ticket actualizado
        return ticketMapper.ticketToTicketDTO(ticket); // Devolver el ticket actualizado en formato DTO
    }

    @Transactional(readOnly = true)
    @Override
    public TicketDTO obtenerTicket(Long id) throws TicketNotFoundException {
        Ticket ticket = obtenerTicketEntity(id);
        return ticketMapper.ticketToTicketDTO(ticket);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TicketDTO> obtenerTicketsPorUsuario(String userId) {
        List<Ticket> tickets = ticketRepository.findByAutorUserId(userId);
        return tickets.stream()
                .map(ticketMapper::ticketToTicketDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TicketDTO> obtenerTicketsPorUsuarioOrdenadosPorFecha(String userId) {
        List<Ticket> tickets = ticketRepository.findByAutorUserIdOrderByFechaCreacionDesc(userId);
        return tickets.stream()
                .map(ticketMapper::ticketToTicketDTO)
                .toList();
    }

    private Ticket obtenerTicketEntity(Long id) throws TicketNotFoundException {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket no encontrado con ID: " + id));
    }
    
}
