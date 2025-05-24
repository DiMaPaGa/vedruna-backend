package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción que indica que un dispositivo no ha sido encontrado.
 * Se lanza cuando se intenta acceder o manipular un dispositivo que no existe en la base de datos.
 */
public class DispositivoNotFoundException extends RuntimeException {
     /**
     * Construye una nueva excepción con un mensaje específico.
     *
     * @param message Mensaje detallado indicando que el dispositivo no fue encontrado.
     */
    public DispositivoNotFoundException(String message) {
        super(message);
    }
    
}
