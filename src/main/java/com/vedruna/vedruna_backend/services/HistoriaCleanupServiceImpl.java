package com.vedruna.vedruna_backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.persistance.models.Historia;
import com.vedruna.vedruna_backend.persistance.repositories.HistoriaRepository;

@Service
public class HistoriaCleanupServiceImpl implements HistoriaCleanupService {

    @Autowired
    private HistoriaRepository historiaRepository;

    // Tarea programada para limpiar historias expiradas
    @Scheduled(cron = "0 0 0 * * *")  // Se ejecuta a las 00:00 todos los días
    public void limpiarHistoriasExpiradas() {
        // Obtener la fecha y hora actuales
        LocalDateTime ahora = LocalDateTime.now();
        
        // Buscar todas las historias que han expirado
        List<Historia> historiasExpiradas = historiaRepository.findByExpiraEnBefore(ahora);
        
        if (!historiasExpiradas.isEmpty()) {
            // Eliminar las historias expiradas
            historiaRepository.deleteAll(historiasExpiradas);
            System.out.println("Historias expiradas eliminadas: " + historiasExpiradas.size());
        } else {
            System.out.println("No se encontraron historias expiradas.");
        }
    }
}
    
