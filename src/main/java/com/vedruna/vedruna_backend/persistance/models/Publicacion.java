package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "publicaciones")
public class Publicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "titulo", length = 40, nullable = false)
    private String titulo;

    @Column(name = "comentario", length = 250)
    private String comentario;

    @Enumerated(EnumType.STRING)
    @Column(name = "privacidad")
    private Privacidad privacidad = Privacidad.PUBLICA;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Relación con los "likes" (uno a muchos)
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;

    // Relación con los comentarios (uno a muchos)
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    
}
