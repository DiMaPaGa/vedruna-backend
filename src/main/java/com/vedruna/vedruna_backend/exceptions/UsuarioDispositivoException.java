package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada para errores relacionados con la asociación
 * entre usuarios y dispositivos.
 */
public class UsuarioDispositivoException extends RuntimeException { 
    
    /**
     * Constructor que crea la excepción con un mensaje específico.
     * @param message mensaje que describe la causa del error
     */
    public UsuarioDispositivoException(String message) {
        super(message);
    }
    
}
