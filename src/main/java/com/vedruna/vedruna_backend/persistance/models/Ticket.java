package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un ticket de soporte o incidencia reportada por un usuario.
 * Contiene información sobre el autor, el equipo o clase afectado, el título, la descripción,
 * una imagen opcional, la fecha de creación y el estado actual del ticket (EN_TRAMITE, por defecto).
 * 
 * Un ticket es creado por un usuario y puede encontrarse en uno de los estados definidos
 * en la enumeración {@link EstadoTicket}, como EN_TRAMITE, SOLUCIONADO o DENEGADO.
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

    /**
     * Identificador único del ticket (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario que creó el ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Usuario autor;

    /**
     * Información sobre el equipo o clase relacionado con la incidencia.
     */
    @Column(name = "equipo_clase", length = 255)
    private String equipoClase;

    /**
     * Título del ticket, con una longitud máxima de 40 caracteres.
     */
    @Column(name = "titulo", length = 40, nullable = false)
    private String titulo;

    /**
     * Descripción detallada del problema o incidencia, hasta 250 caracteres.
     */
    @Column(name = "descripcion", length = 250, nullable = false)
    private String descripcion;

    /**
     * URL de una imagen opcional asociada con el ticket.
     */
    @Column(name = "image_url")
    private String imageUrl; 

    /**
     * Fecha y hora de creación del ticket.
     */
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    /**
     * Estado actual del ticket. Puede ser EN_TRAMITE, SOLUCIONADO o DENEGADO.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoTicket estado = EstadoTicket.EN_TRAMITE;

    /**
     * Método de ciclo de vida que se ejecuta antes de insertar el ticket en la base de datos.
     * Inicializa la fecha de creación si aún no se ha definido.
     */
    @PrePersist
    public void onCreate() {
        if (this.fechaCreacion == null){
            this.fechaCreacion = LocalDateTime.now();
        }
    }


    
}
