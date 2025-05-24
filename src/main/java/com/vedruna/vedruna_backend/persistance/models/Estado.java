package com.vedruna.vedruna_backend.persistance.models;

/**
 * Enumeración que representa el estado de la  solicitud de seguimiento entre usuarios.
 */
public enum Estado {
    /**
     * Estado pendiente, indica que la acción está en espera de aprobación o procesamiento.
     */
    PENDIENTE, 
    
    /**
     * Estado aceptado, indica que la acción ha sido aprobada y procesada exitosamente.
     */    
    ACEPTADO
}