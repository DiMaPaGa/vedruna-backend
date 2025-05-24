package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción que indica que el acceso a un recurso o acción está denegado.
 * Se lanza cuando un usuario intenta realizar una operación para la cual no tiene permisos.
 */
public class AccessDeniedException extends RuntimeException {

    /**
     * Construye una nueva excepción con un mensaje específico.
     * 
     * @param message Mensaje detallado de la causa de la denegación de acceso.
     */
    public AccessDeniedException(String message) {
        super(message);
    }
}
