package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase embebible que representa la clave primaria compuesta para la entidad {@link Seguidor}.
 * Contiene los identificadores del usuario que sigue (seguidor) y del usuario seguido.
 * Esta clase debe implementar correctamente los métodos {@code equals} y {@code hashCode}
 * para asegurar el correcto funcionamiento con JPA.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguidorId implements Serializable {

    /**
     * ID del usuario que sigue a otro usuario.
     */
    @Column(name = "seguidor_id")
    private String seguidorId;

    /**
     * ID del usuario que es seguido.
     */
    @Column(name = "seguido_id")
    private String seguidoId;


    /**
     * Compara este objeto con otro para determinar igualdad.
     * Se considera igual si ambos IDs (seguidor y seguido) son iguales.
     *
     * @param o el objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeguidorId that = (SeguidorId) o;
        return seguidorId.equals(that.seguidorId) &&
               seguidoId.equals(that.seguidoId);
    }

     /**
     * Devuelve un valor hash basado en los IDs del seguidor y seguido.
     *
     * @return código hash
     */
    @Override
    public int hashCode() {
        return 31 * seguidorId.hashCode() + seguidoId.hashCode();
    }
    
}
