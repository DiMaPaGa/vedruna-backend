package com.vedruna.vedruna_backend.persistance.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Historia;

@Repository
public interface HistoriaRepository extends JpaRepository<Historia, Long> {

    // Buscar todas las historias de un usuario específico
    List<Historia> findByAutor_UserId(String userId);

    // Buscar historias que no hayan expirado (expiraEn > fecha actual)
    List<Historia> findByExpiraEnAfter(LocalDateTime currentDate);

    // Buscar las historias de un usuario ordenadas por fecha de creación (más recientes primero)
    List<Historia> findByAutorUserIdOrderByCreatedAtDesc(String userId);

    // Buscar todas las historias que expiran antes de una fecha específica
    List<Historia> findByExpiraEnBefore(LocalDateTime expirationDate);

    
}
