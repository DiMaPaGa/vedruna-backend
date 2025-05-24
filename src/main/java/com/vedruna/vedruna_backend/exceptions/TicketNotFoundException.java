package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que un ticket no fue encontrado.
 * Se lanza cuando no existe un ticket con el identificador proporcionado.
 */
public class TicketNotFoundException extends RuntimeException {

    /**
     * Constructor que crea la excepción con un mensaje específico.
     * @param message mensaje que describe la causa de la excepción
     */
    public TicketNotFoundException(String message) {
        super(message);
    }
    
}
