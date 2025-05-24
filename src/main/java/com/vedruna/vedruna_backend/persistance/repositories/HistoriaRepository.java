package com.vedruna.vedruna_backend.persistance.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Historia;
import com.vedruna.vedruna_backend.persistance.models.Usuario;

/**
 * Repositorio para la entidad {@link Historia}.
 * Permite realizar operaciones CRUD y consultas personalizadas sobre las historias publicadas por los usuarios.
 *
 * Extiende {@link JpaRepository}, proporcionando acceso a funcionalidades estándar como guardar, buscar y eliminar.
 */
@Repository
public interface HistoriaRepository extends JpaRepository<Historia, Long> {

    /**
     * Obtiene las historias activas (no expiradas) de un usuario específico.
     *
     * @param userId ID del usuario (campo único de {@link Usuario}).
     * @param now    Fecha y hora actual para filtrar historias que no han expirado.
     * @return Lista de historias activas del usuario.
     */
    List<Historia> findByAutorUserIdAndExpiraEnAfter(String userId, LocalDateTime now);

    /**
     * Obtiene todas las historias que han expirado.
     *
     * @param now Fecha y hora actual para comparar contra la fecha de expiración.
     * @return Lista de historias cuya fecha de expiración es anterior al momento actual.
     */
    List<Historia> findByExpiraEnBefore(LocalDateTime now);

    
}
