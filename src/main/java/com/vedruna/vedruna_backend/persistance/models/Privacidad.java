package com.vedruna.vedruna_backend.persistance.models;

/**
 * Enum que representa los niveles de privacidad para una entidad,
 * como puede ser una publicación o contenido en la aplicación.
 * 
 * - PUBLICA: El contenido es visible para todos los usuarios.
 * - PRIVADA: El contenido es visible solo para el propietario y sus seguidores confirmados.
 *   
 */
public enum Privacidad {
    PUBLICA, 
    
    PRIVADA
}