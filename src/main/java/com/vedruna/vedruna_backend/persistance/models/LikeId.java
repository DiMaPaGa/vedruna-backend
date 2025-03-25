package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class LikeId implements Serializable{
    @Column(name = "user_id")
    private String userId;

    @Column(name = "publicacion_id")
    private Long publicacionId;

}
