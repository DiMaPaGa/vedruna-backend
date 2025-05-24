package com.vedruna.vedruna_backend.controllers;

import com.vedruna.vedruna_backend.dto.TicketDTO;
import com.vedruna.vedruna_backend.exceptions.TicketNotFoundException;
import com.vedruna.vedruna_backend.services.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Operation(summary = "Crear un nuevo ticket")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ticket creado correctamente",
                     content = @Content(schema = @Schema(implementation = TicketDTO.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<TicketDTO> crearTicket(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos del nuevo ticket",
        required = true,
        content = @Content(schema = @Schema(implementation = TicketDTO.class))
    )
        @RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.crearTicket(ticketDTO);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un ticket existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> actualizarTicket(
        @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del ticket actualizado",
            required = true,
            content = @Content(schema = @Schema(implementation = TicketDTO.class))
        )
        @RequestBody TicketDTO ticketDTO) {
        try {
            TicketDTO updatedTicket = ticketService.actualizarTicket(id, ticketDTO);
            return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
        } catch (TicketNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener un ticket por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket encontrado",
                     content = @Content(schema = @Schema(implementation = TicketDTO.class))),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> obtenerTicket(@PathVariable Long id) {
        try {
            TicketDTO ticketDTO = ticketService.obtenerTicket(id);
            return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
        } catch (TicketNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener todos los tickets de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tickets",
                     content = @Content(schema = @Schema(implementation = TicketDTO.class)))
    })
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<TicketDTO>> obtenerTicketsPorUsuario(@PathVariable String userId) {
        List<TicketDTO> ticketDTOs = ticketService.obtenerTicketsPorUsuario(userId);
        return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todos los tickets de un usuario ordenados por fecha")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tickets ordenados",
                     content = @Content(schema = @Schema(implementation = TicketDTO.class)))
    })
    @GetMapping("/usuario/{userId}/ordenados")
    public ResponseEntity<List<TicketDTO>> obtenerTicketsPorUsuarioOrdenados(@PathVariable String userId) {
        List<TicketDTO> ticketDTOs = ticketService.obtenerTicketsPorUsuarioOrdenadosPorFecha(userId);
        return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
    }
}
