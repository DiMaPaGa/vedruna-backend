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

/**
 * Representa un "like" dado por un usuario a una publicación.
 * La entidad utiliza una clave compuesta {@link LikeId} que contiene
 * la referencia al usuario y a la publicación a la que se le dio like.
 * Relaciona un usuario con una publicación que ha recibido un "like".
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "likes")
public class Like implements Serializable {

    /**
     * Identificador compuesto que contiene el userId y publicacionId.
     */
    @EmbeddedId
    private LikeId likeId;

    /**
     * Usuario que dio el "like".
     * Mapeado para cargar el usuario relacionado a partir del userId en la clave compuesta.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Usuario usuario;

    /**
     * Publicación que recibió el "like".
     * Mapeado para cargar la publicación relacionada a partir del publicacion_id en la clave compuesta.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", insertable = false, updatable = false)
    private Publicacion publicacion;

    
}
