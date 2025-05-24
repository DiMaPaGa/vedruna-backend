package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa una publicación realizada por un usuario en la plataforma.
 * Cada publicación puede incluir una imagen, un título, una descripción, 
 * y tiene un nivel de privacidad definido.
 * Además, puede estar asociada a múltiples "likes" y comentarios.
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "publicaciones")
public class Publicacion implements Serializable {

    /**
     * Identificador único de la publicación.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario que ha creado la publicación.
     * Relación ManyToOne con la entidad {@link Usuario}.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Usuario autor;

    /**
     * URL de la imagen asociada a la publicación (opcional).
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * Título de la publicación.
     * Longitud máxima: 40 caracteres.
     */
    @Column(name = "titulo", length = 40, nullable = false)
    private String titulo;

    /**
     * Comentario o descripción adicional de la publicación.
     * Longitud máxima: 250 caracteres.
     */
    @Column(name = "comentario", length = 250)
    private String comentario;

    /**
     * Nivel de privacidad de la publicación: PUBLICA o PRIVADA.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "privacidad",  nullable = false)
    private Privacidad privacidad = Privacidad.PUBLICA;

    /**
     * Fecha y hora en que se creó la publicación.
     * Se establece automáticamente antes de persistir.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Lista de "likes" asociados a esta publicación.
     * Relación OneToMany con la entidad {@link Like}.
     */
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>(); 

    /**
     * Lista de comentarios asociados a esta publicación.
     * Relación OneToMany con la entidad {@link Comentario}.
     */
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios;

    /**
     * Método de ciclo de vida de JPA que establece la fecha de creación
     * antes de que la entidad sea persistida en la base de datos.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    
}
