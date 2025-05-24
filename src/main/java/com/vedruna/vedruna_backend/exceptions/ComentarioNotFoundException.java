package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción que indica que un comentario no ha sido encontrado.
 * Se lanza cuando se intenta acceder o manipular un comentario que no existe en la base de datos.
 */
public class ComentarioNotFoundException extends RuntimeException {

    /**
     * Construye una nueva excepción con un mensaje específico.
     *
     * @param message Mensaje detallado indicando que el comentario no fue encontrado.
     */
    public ComentarioNotFoundException(String message) {
        super(message);  
    }   
}
