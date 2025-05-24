package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que ya existe un seguimiento previo.
 * Se lanza cuando se intenta crear un seguimiento duplicado entre usuarios.
 */
public class SeguimientoExistenteException extends RuntimeException{

    /**
     * Constructor que crea la excepción con un mensaje detallado.
     * @param mensaje mensaje que describe el motivo de la excepción
     */
    public SeguimientoExistenteException(String mensaje) {
        super(mensaje);
    }
    
}
