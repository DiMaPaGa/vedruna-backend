package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que un seguidor no fue encontrado.
 * Se lanza cuando la entidad seguidor solicitada no existe en la base de datos.
 */
public class SeguidorNotFoundException extends RuntimeException{

    /**
     * Constructor que crea la excepción con un mensaje detallado.
     * @param message mensaje que describe el motivo de la excepción
     */
    public SeguidorNotFoundException(String message) {
        super(message);
    }
    
}
