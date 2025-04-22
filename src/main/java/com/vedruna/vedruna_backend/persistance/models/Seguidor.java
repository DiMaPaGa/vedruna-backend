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

@NoArgsConstructor
@Data
@Entity
@Table(name = "seguidores")
public class Seguidor implements Serializable{

    @EmbeddedId
    private SeguidorId id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "seguidor_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Usuario seguidor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "seguido_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Usuario seguido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado",  nullable = false)
    private Estado estado = Estado.PENDIENTE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
}
