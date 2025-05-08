package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    Optional<Dispositivo> findByExpoPushId(String expoPushId);
    Optional<Dispositivo> findById(Long id);

    
    
}
