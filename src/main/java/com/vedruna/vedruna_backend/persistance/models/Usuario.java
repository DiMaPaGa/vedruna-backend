package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
//@Cacheable("usuarios")  // <-- Marca esta entidad para caché
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;  // Google UID

    @Column(nullable = false)
    private String email;  // Correo electrónico del usuario

    @Column(nullable = false)
    private String givenName;  // Nombre del usuario (given_name de Google)

    @Column(name = "profile_image_url", nullable = true)
    private String profileImageUrl;  // URL de la foto de perfil (proporcionada por Cloudinary)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Fecha de creación (timestamp)

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
  
}
