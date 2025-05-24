package com.vedruna.vedruna_backend.persistance.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una imagen asociada a una historia.
 * Cada imagen pertenece a una historia y puede contener un texto descriptivo
 * y un orden para definir la secuencia de las imágenes dentro de la historia.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "historias_imagenes")
public class HistoriaImagen implements Serializable {

    /**
     * Identificador único de la imagen.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Historia a la que pertenece esta imagen.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historia_id", nullable = false)
    private Historia historia;

    /**
     * URL de la imagen.
     */
    @Column(name = "imagen_url", nullable = false)
    private String imagenUrl;

    /**
     * Texto descriptivo asociado a la imagen (opcional).
     */
    @Column(name = "texto")
    private String texto;

    /**
     * Orden de la imagen dentro de la historia. 
     * Se usa para determinar la posición en la secuencia de imágenes.
     * Valor por defecto: 0.
     */
    @Column(name = "orden")
    private Integer orden = 0;
    
}
