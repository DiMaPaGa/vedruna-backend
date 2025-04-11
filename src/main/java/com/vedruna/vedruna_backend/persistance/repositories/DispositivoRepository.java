package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    // Buscar todos los dispositivos de un usuario específico
    List<Dispositivo> findByUsuario_UserId(String userId);

    // Buscar un dispositivo por su expoPushToken (único)
    Optional<Dispositivo> findByExpoPushToken(String expoPushToken);

    // Eliminar un dispositivo por su expoPushToken
    void deleteByExpoPushToken(String expoPushToken);


    
}
