package com.vedruna.vedruna_backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.persistance.models.Historia;
import com.vedruna.vedruna_backend.persistance.repositories.HistoriaRepository;

/**
 * Implementación del servicio para la limpieza de historias expiradas.
 * Esta clase contiene la lógica para eliminar historias que hayan expirado,
 * ejecutándose automáticamente todos los días a medianoche.
 */
@Service
public class HistoriaCleanupServiceImpl implements HistoriaCleanupService {

    @Autowired
    private HistoriaRepository historiaRepository;

    /**
     * Tarea programada para limpiar las historias expiradas.
     * Se ejecuta a las 00:00 todos los días.
     * 
     * Este método obtiene la fecha y hora actuales, consulta todas las historias
     * cuya fecha de expiración ya pasó y las elimina de la base de datos.
     */
    @Scheduled(cron = "0 0 0 * * *")  
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
    
