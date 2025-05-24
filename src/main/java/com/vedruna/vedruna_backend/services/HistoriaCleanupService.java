package com.vedruna.vedruna_backend.services;

/**
 * Servicio para la limpieza de historias expiradas.
 */
public interface HistoriaCleanupService {

    /**
     * Ejecuta la limpieza de historias expiradas.
     */
    void limpiarHistoriasExpiradas();
    
}
