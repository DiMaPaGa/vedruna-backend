package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa la relación entre un {@link Usuario} y un {@link Dispositivo}.
 * Permite vincular múltiples dispositivos a un mismo usuario y viceversa.
 * 
 * Se utiliza para rastrear qué dispositivos están registrados por cada usuario, útil para notificaciones push).
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "usuario_dispositivo")
public class UsuarioDispositivo implements Serializable {

    /**
     * Identificador único de la relación usuario-dispositivo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relación con el usuario que ha registrado el dispositivo.
     * Esta relación es obligatoria.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    /**
     * Relación con el dispositivo registrado por el usuario.
     * Esta relación es obligatoria.
     */
    @ManyToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private Dispositivo dispositivo;

    /**
     * Fecha y hora en que se registró la relación usuario-dispositivo.
     * Se establece automáticamente al persistir.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Inicializa automáticamente la fecha de creación antes de persistir la entidad.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Constructor útil para crear instancias a partir de un usuario y un dispositivo.
     *
     * @param usuario el usuario al que se asocia el dispositivo
     * @param dispositivo el dispositivo que se asocia al usuario
     */
    public UsuarioDispositivo(Usuario usuario, Dispositivo dispositivo) {
        this.usuario = usuario;
        this.dispositivo = dispositivo;
    }
}
    
