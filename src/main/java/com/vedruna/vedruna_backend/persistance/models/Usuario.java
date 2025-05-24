package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa a un usuario en el sistema. Los usuarios están autenticados 
 * mediante OAuth (Google) y tienen un identificador único, correo electrónico,
 * nombre, imagen de perfil y una fecha de creación. También se relacionan con múltiples dispositivos.
 * 
 * La entidad contiene un campo `userId` que es único y corresponde al identificador
 * externo del proveedor de autenticación (Google).
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

    /**
     * Identificador interno del usuario (clave primaria en la base de datos).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identificador único del usuario proporcionado por un proveedor externo de autenticación
     * (en este caso, Google OAuth).
     */
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId; 

    /**
     * Correo electrónico del usuario. No puede ser nulo.
     */
    @Column(nullable = false)
    private String email;  

    /**
     * Nombre del usuario (el "given_name" proporcionado por Google).
     */
    @Column(nullable = false)
    private String givenName;

    /**
     * URL de la imagen de perfil del usuario. Puede ser nula si el usuario no ha proporcionado una.
     */
    @Column(name = "profile_image_url", nullable = true)
    private String profileImageUrl;

    /**
     * Fecha y hora en que se creó el usuario en el sistema.
     * Se establece automáticamente antes de persistir.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Fecha de creación (timestamp)

    /**
     * Método de ciclo de vida JPA que se ejecuta antes de insertar la entidad.
     * Se asegura de que el campo `createdAt` esté inicializado.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Lista de relaciones entre el usuario y sus dispositivos registrados.
     * Cada relación se representa con un objeto {@link UsuarioDispositivo}.
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioDispositivo> dispositivos = new ArrayList<>();
  
}
