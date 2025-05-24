package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que un "Like" no fue encontrado.
 * Se lanza cuando se intenta acceder o eliminar un "Like" que no existe.
 */
public class LikeNotFoundException extends RuntimeException {

    /**
     * Constructor que crea la excepción con un mensaje personalizado.
     * @param message mensaje detallado de la excepción
     */
    public LikeNotFoundException(String message) {
        super(message);
    }
}
