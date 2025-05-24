package com.vedruna.vedruna_backend.persistance.models;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un comentario realizado por un usuario sobre una publicación.
 * Soporta comentarios anidados (respuestas a otros comentarios).
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "comentarios")
public class Comentario implements Serializable {

    /**
     * Identificador único del comentario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     /**
     * Usuario que realizó el comentario.
     * Relación ManyToOne con la entidad Usuario.
     */
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
     private Usuario autor;

    /**
     * Publicación a la que pertenece el comentario.
     * Relación ManyToOne con la entidad Publicacion.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    /**
     * Texto del comentario.
     * Campo obligatorio con tipo TEXT en la base de datos.
     */
    @Column(name = "comentario", columnDefinition = "TEXT", nullable = false)
    private String comentario;

    /**
     * Comentario padre en caso de ser una respuesta a otro comentario.
     * Relación ManyToOne con la misma entidad Comentario.
     * Se carga de forma perezosa.
     * Ignorado en la serialización JSON para evitar recursión infinita.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore 
    private Comentario comentarioPadre;

    /**
     * Lista de respuestas (comentarios hijos) asociados a este comentario.
     * Relación OneToMany mapeada por la propiedad comentarioPadre.
     * Cascade ALL para persistencia en cascada.
     * Se carga de forma perezosa.
     * Se incluye en el JSON solo si no está vacía.
     */
    @OneToMany(mappedBy = "comentarioPadre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Comentario> respuestas;

    /**
     * Fecha y hora en la que se creó el comentario.
     * Se establece automáticamente antes de persistir el objeto.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Método llamado automáticamente antes de persistir la entidad.
     * Establece el valor de createdAt con la fecha y hora actuales.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

