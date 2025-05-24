package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa la relación de seguimiento entre dos usuarios.
 * Un usuario (seguidor) puede seguir a otro usuario (seguido).
 * Esta relación puede tener diferentes estados, como PENDIENTE o ACEPTADO.
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "seguidores")
public class Seguidor implements Serializable{

     /**
     * Identificador embebido que representa la clave compuesta 
     * formada por el ID del seguidor y el ID del seguido.
     */
    @EmbeddedId
    private SeguidorId id;

    /**
     * Usuario que sigue a otro usuario.
     * Relación ManyToOne con la entidad {@link Usuario}.
     * Este campo no es insertable ni actualizable ya que está contenido en la clave primaria.
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "seguidor_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Usuario seguidor;

    /**
     * Usuario que es seguido por otro usuario.
     * Relación ManyToOne con la entidad {@link Usuario}.
     * Este campo no es insertable ni actualizable ya que está contenido en la clave primaria.
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "seguido_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Usuario seguido;

    /**
     * Estado de la relación de seguimiento. 
     * Puede ser PENDIENTE o ACEPTADO.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado",  nullable = false)
    private Estado estado = Estado.PENDIENTE;

    /**
     * Fecha y hora en que se creó esta relación de seguimiento.
     * Se establece automáticamente antes de persistir la entidad.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Método de ciclo de vida de JPA que establece la fecha de creación
     * automáticamente antes de que la entidad sea persistida.
     */
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Constructor que permite crear una instancia de Seguidor
     * con una clave compuesta y un estado específico.
     *
     * @param id     Identificador compuesto (seguidor_id y seguido_id)
     * @param estado Estado de la relación (PENDIENTE o ACEPTADO)
     */
    public Seguidor(SeguidorId id, Estado estado) {
        this.id = id;
        this.estado = estado;
    }
    
}
