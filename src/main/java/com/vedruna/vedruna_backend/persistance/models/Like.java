package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "likes")
public class Like implements Serializable {
    @EmbeddedId
    private LikeId likeId;

    // Relación con la publicación que recibió el like
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", nullable = false, insertable = false, updatable = false)  // Hibernate gestionará la relación
    private Publicacion publicacion;
    
}
