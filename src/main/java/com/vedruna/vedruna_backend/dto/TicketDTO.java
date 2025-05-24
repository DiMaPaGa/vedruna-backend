package com.vedruna.vedruna_backend.dto;

import java.time.LocalDateTime;

import com.vedruna.vedruna_backend.persistance.models.EstadoTicket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa un ticket")
public class TicketDTO {
    @Schema(description = "ID único del ticket", example = "852")
    private Long id;

    @Schema(description = "Usuario autor del ticket")
    private UsuarioDTO autor;

    @Schema(description = "Clase o tipo de equipo relacionado con el ticket", example = "Pantalla")
    private String equipoClase;

    @Schema(description = "Título del ticket", example = "Problema con la Pantalla")
    private String titulo;

    @Schema(description = "Descripción detallada del problema", example = "La pantalla no enciende")
    private String descripcion;

    @Schema(description = "URL de la imagen relacionada al ticket", example = "https://example.com/pantalla.jpg")
    private String imageUrl;

    @Schema(description = "Fecha y hora de creación del ticket")
    private LocalDateTime fechaCreacion;

    @Schema(description = "Estado actual del ticket")
    private EstadoTicket estado;
    
}
