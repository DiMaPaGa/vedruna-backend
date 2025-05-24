package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Representa una Historia publicada por un usuario.
 * Cada historia tiene un autor, fecha de creación, fecha de expiración
 * y una lista de imágenes asociadas.
 * Las historias expiran automáticamente tras un cierto tiempo definido en {@code expiraEn}.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "historias")
public class Historia implements Serializable{

    /**
     * Identificador único de la historia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Usuario que creó la historia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario autor;

    /**
     * Fecha y hora en que se creó la historia.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Fecha y hora en que la historia expira y deja de ser visible.
     */
    @Column(name = "expira_en", nullable = false)
    private LocalDateTime expiraEn;  

    /**
     * Método callback que se ejecuta antes de persistir la entidad,
     * asigna la fecha y hora actual a {@code createdAt}.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Lista de imágenes asociadas a esta historia.
     * La relación es de uno a muchos; al eliminar una historia,
     * también se eliminan las imágenes asociadas.
     */
    @OneToMany(mappedBy = "historia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HistoriaImagen> imagenes = new ArrayList<>();
    
}
