package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que una publicación no fue encontrada.
 * Se lanza cuando se intenta acceder a una publicación que no existe en la base de datos.
 */
public class PublicacionNotFoundException extends RuntimeException {

    /**
     * Constructor que crea la excepción con un mensaje personalizado.
     * @param message mensaje detallado de la excepción
     */
    public PublicacionNotFoundException(String message) {
        super(message);
    }
    
}
