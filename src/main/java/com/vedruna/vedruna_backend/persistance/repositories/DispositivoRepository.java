package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Dispositivo;

/**
 * Repositorio para la entidad {@link Dispositivo}.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas
 * sobre los dispositivos registrados (por ejemplo, para notificaciones push).
 * 
 * <p>Extiende {@link JpaRepository}, lo que proporciona métodos estándar como 
 * {@code save()}, {@code findById()}, {@code delete()}, etc.</p>
 */
@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    /**
     * Busca un dispositivo por su identificador de notificaciones Expo Push.
     *
     * @param expoPushId Identificador único de Expo Push.
     * @return Un {@link Optional} que contiene el dispositivo si existe, o vacío si no se encuentra.
     */
    Optional<Dispositivo> findByExpoPushId(String expoPushId);

    /**
     * Busca un dispositivo por su ID en la base de datos.
     *
     * @param id ID del dispositivo.
     * @return Un {@link Optional} que contiene el dispositivo si existe, o vacío si no se encuentra.
     */
    Optional<Dispositivo> findById(Long id);
    
}
