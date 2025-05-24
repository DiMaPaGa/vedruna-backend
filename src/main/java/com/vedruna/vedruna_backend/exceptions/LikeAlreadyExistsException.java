package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que un "Like" ya existe para una publicación dada.
 * Se lanza cuando se intenta agregar un "Like" duplicado.
 */
public class LikeAlreadyExistsException extends RuntimeException{
    /**
     * Constructor que crea la excepción con un mensaje personalizado.
     * @param message mensaje detallado de la excepción
     */
    public LikeAlreadyExistsException(String message) {
        super(message);
    }
    
}
