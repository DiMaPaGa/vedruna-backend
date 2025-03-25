package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguidorId implements Serializable {

    @Column(name = "seguidor_id")
    private String seguidorId;

    @Column(name = "seguido_id")
    private String seguidoId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeguidorId that = (SeguidorId) o;
        return seguidorId.equals(that.seguidorId) &&
               seguidoId.equals(that.seguidoId);
    }

    @Override
    public int hashCode() {
        return 31 * seguidorId.hashCode() + seguidoId.hashCode();
    }
    
}
