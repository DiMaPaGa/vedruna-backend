package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.vedruna_backend.persistance.models.Historia;
import com.vedruna.vedruna_backend.persistance.models.HistoriaImagen;

/**
 * Repositorio para la entidad {@link HistoriaImagen}.
 * Permite realizar operaciones CRUD y consultas específicas relacionadas
 * con las imágenes asociadas a una {@link Historia}.
 *
 * <p>Extiende {@link JpaRepository}, proporcionando métodos como {@code save()},
 * {@code findAll()}, {@code delete()}, etc.</p>
 */
public interface HistoriaImagenRepository extends JpaRepository<HistoriaImagen, Long> {
    
    /**
     * Obtiene todas las imágenes asociadas a una historia específica.
     *
     * @param historiaId ID de la historia.
     * @return Lista de {@link HistoriaImagen} asociadas a la historia proporcionada.
     */
    List<HistoriaImagen> findByHistoriaId(Long historiaId);
    
}
