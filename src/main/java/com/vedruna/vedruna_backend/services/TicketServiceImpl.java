package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.exceptions.TicketNotFoundException;
import com.vedruna.vedruna_backend.mappers.TicketMapper;
import com.vedruna.vedruna_backend.persistance.models.Ticket;
import com.vedruna.vedruna_backend.persistance.repositories.TicketRepository;

/**
 * Implementación de la interfaz TicketService.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UsuarioService usuarioService;  // Verificar que el usuario existe

    @Autowired
    private EmailService emailService;

    @Value("${app.admin-email}")
    private String adminEmail;

    /**
     * Crea un nuevo ticket en la base de datos, envía un email de notificación al administrador
     * y devuelve el ticket creado en formato de DTO.
     * 
     * @param ticketDTO Información del ticket a crear
     * @return Ticket creado en formato de DTO
     */
    @Transactional
    @Override
    public TicketDTO crearTicket(TicketDTO ticketDTO) {
        
        // Convertir el DTO a la entidad Ticket
        Ticket ticket = ticketMapper.ticketDTOToTicket(ticketDTO);
        ticket = ticketRepository.save(ticket); // Guardar en la base de datos

        // 📨 Enviar email de aviso al admin
        String subject = "Nuevo ticket creado";
        String body = String.format(
            "Se ha creado un nuevo ticket:\n\nTítulo: %s\nDescripción: %s\nEquipo: %s\nEstado: %s\n",
            ticket.getTitulo(),
            ticket.getDescripcion(),
            ticket.getEquipoClase(),
            ticket.getEstado()
        );
        emailService.sendEmail(adminEmail, subject, body);

        return ticketMapper.ticketToTicketDTO(ticket);
    }

    /**
     * Actualiza un ticket existente en la base de datos y devuelve el ticket
     * actualizado en formato de DTO.
     * 
     * @param id        ID del ticket a actualizar
     * @param ticketDTO Información del ticket actualizado
     * @return Ticket actualizado en formato de DTO
     * @throws TicketNotFoundException si el ticket no existe
     */
    @Transactional
    @Override
    public TicketDTO actualizarTicket(Long id, TicketDTO ticketDTO) throws TicketNotFoundException {
        // Verificar si el ticket existe
        Ticket ticket = obtenerTicketEntity(id);
        ticket.setTitulo(ticketDTO.getTitulo());
        ticket.setDescripcion(ticketDTO.getDescripcion());
        ticket.setEquipoClase(ticketDTO.getEquipoClase());
        ticket.setImageUrl(ticketDTO.getImageUrl());
        ticket.setEstado(ticketDTO.getEstado());
        ticket = ticketRepository.save(ticket);
        return ticketMapper.ticketToTicketDTO(ticket);
    }

/**
 * Obtiene un ticket por su ID desde la base de datos y lo devuelve en formato de DTO.
 * 
 * @param id ID del ticket a obtener
 * @return Ticket encontrado en formato de DTO
 * @throws TicketNotFoundException si el ticket no existe
 */
    @Transactional(readOnly = true)
    @Override
    public TicketDTO obtenerTicket(Long id) throws TicketNotFoundException {
        Ticket ticket = obtenerTicketEntity(id);
        return ticketMapper.ticketToTicketDTO(ticket);
    }

    /**
     * Obtiene todos los tickets de un usuario desde la base de datos y devuelve la
     * lista de tickets en formato de DTO.
     * 
     * @param userId ID del usuario autor de los tickets
     * @return Lista de tickets asociados al usuario en formato de DTO
     */
    @Transactional(readOnly = true)
    @Override
    public List<TicketDTO> obtenerTicketsPorUsuario(String userId) {
        List<Ticket> tickets = ticketRepository.findByAutorUserId(userId);
        return tickets.stream()
                .map(ticketMapper::ticketToTicketDTO)
                .toList();
    }

    /**
     * Obtiene todos los tickets de un usuario desde la base de datos y devuelve la
     * lista de tickets en formato de DTO, ordenados por fecha de creación en orden
     * descendente (más recientes primero).
     * 
     * @param userId ID del usuario autor de los tickets
     * @return Lista de tickets ordenados por fecha de creación descendente en formato de DTO
     */
    @Transactional(readOnly = true)
    @Override
    public List<TicketDTO> obtenerTicketsPorUsuarioOrdenadosPorFecha(String userId) {
        List<Ticket> tickets = ticketRepository.findByAutorUserIdOrderByFechaCreacionDesc(userId);
        return tickets.stream()
                .map(ticketMapper::ticketToTicketDTO)
                .toList();
    }

    /**
     * Obtiene un ticket por su ID desde la base de datos y lo devuelve como
     * entidad Ticket.
     * 
     * @param id ID del ticket a obtener
     * @return Ticket encontrado como entidad Ticket
     * @throws TicketNotFoundException si el ticket no existe
     */
    private Ticket obtenerTicketEntity(Long id) throws TicketNotFoundException {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket no encontrado con ID: " + id));
    }
    
}
