package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que un recurso no fue encontrado.
 * Se utiliza cuando una entidad o recurso solicitado no existe en la base de datos o sistema.
 */
public class ResourceNotFoundException extends RuntimeException{
    
    /**
     * Constructor que crea la excepción con un mensaje personalizado.
     * @param message mensaje detallado de la excepción
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
