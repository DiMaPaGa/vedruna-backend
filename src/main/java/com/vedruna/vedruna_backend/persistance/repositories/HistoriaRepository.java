package com.vedruna.vedruna_backend.persistance.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.vedruna_backend.persistance.models.Historia;

@Repository
public interface HistoriaRepository extends JpaRepository<Historia, Long> {

    List<Historia> findByAutorUserIdAndExpiraEnAfter(String userId, LocalDateTime now);
    List<Historia> findByExpiraEnBefore(LocalDateTime now);

    
}
