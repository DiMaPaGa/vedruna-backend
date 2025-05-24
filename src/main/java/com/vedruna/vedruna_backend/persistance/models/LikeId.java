package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la clave primaria compuesta para la entidad {@link Like}.
 * Esta clase embebida contiene los campos que identifican unívocamente un "like":
 * - userId: identificador del usuario que dio el like.
 * - publicacionId: identificador de la publicación que recibió el like.
 * Debe ser Serializable para que JPA pueda usarla como clave primaria compuesta.
 */
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class LikeId implements Serializable{

    /**
     * Identificador del usuario que realizó el like.
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * Identificador de la publicación que recibió el like.
     */
    @Column(name = "publicacion_id")
    private Long publicacionId;

}
