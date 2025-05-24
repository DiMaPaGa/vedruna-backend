package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un dispositivo registrado para recibir notificaciones push.
 * Cada dispositivo tiene un identificador único para Expo Push Notifications.
 * 
 * Se mapea a la tabla "dispositivos" en la base de datos.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "dispositivos")
public class Dispositivo implements Serializable {

    /**
     * Identificador único del dispositivo.
     * Se genera automáticamente con estrategia IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identificador único del dispositivo para Expo Push Notifications.
     * Campo único y obligatorio.
     */
    @Column(name = "expo_push_id", nullable = false, unique = true)
    private String expoPushId;

    /**
     * Fecha y hora en la que se registró el dispositivo.
     * Campo no modificable tras la creación.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    /**
     * Método llamado automáticamente antes de persistir la entidad.
     * Establece el valor de createdAt con la fecha y hora actuales.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Lista de asociaciones entre usuarios y dispositivos.
     * Relación OneToMany con la entidad UsuarioDispositivo.
     */
    @OneToMany(mappedBy = "dispositivo")
    private List<UsuarioDispositivo> usuarioDispositivos = new ArrayList<>();


}
