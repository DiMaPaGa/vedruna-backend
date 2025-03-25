package com.vedruna.vedruna_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.exceptions.TicketNotFoundException;
import com.vedruna.vedruna_backend.services.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Crear un nuevo ticket
    @PostMapping
    public ResponseEntity<TicketDTO> crearTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.crearTicket(ticketDTO);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    // Actualizar un ticket existente
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> actualizarTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        if (ticketDTO == null) {
            // Retorna una respuesta de error si el ticketDTO es null
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            TicketDTO updatedTicket = ticketService.actualizarTicket(id, ticketDTO);
            return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
        } catch (TicketNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Obtener un ticket por ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> obtenerTicket(@PathVariable Long id) {
        try {
            TicketDTO ticketDTO = ticketService.obtenerTicket(id);
            return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
        } catch (TicketNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todos los tickets de un usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<TicketDTO>> obtenerTicketsPorUsuario(@PathVariable String userId) {
        List<TicketDTO> ticketDTOs = ticketService.obtenerTicketsPorUsuario(userId);
        return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
    }

    // Obtener todos los tickets de un usuario ordenados por fecha
    @GetMapping("/usuario/{userId}/ordenados")
    public ResponseEntity<List<TicketDTO>> obtenerTicketsPorUsuarioOrdenados(@PathVariable String userId) {
        List<TicketDTO> ticketDTOs = ticketService.obtenerTicketsPorUsuarioOrdenadosPorFecha(userId);
        return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
    }
    
}
