package com.vedruna.vedruna_backend.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.vedruna_backend.persistance.models.HistoriaImagen;

public interface HistoriaImagenRepository extends JpaRepository<HistoriaImagen, Long> {
    List<HistoriaImagen> findByHistoriaId(Long historiaId);
    
}
