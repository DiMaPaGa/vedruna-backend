package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;

import com.vedruna.vedruna_backend.persistance.models.EstadoTicket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private UsuarioDTO autor; 
    private String equipoClase;
    private String titulo;
    private String descripcion;
    private String imageUrl;
    private LocalDateTime fechaCreacion;
    private EstadoTicket estado;
    
}
