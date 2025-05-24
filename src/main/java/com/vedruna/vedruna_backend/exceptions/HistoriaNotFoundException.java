package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que una Historia no fue encontrada.
 * Se lanza típicamente cuando no existe una entidad Historia con el ID especificado.
 */
public class HistoriaNotFoundException extends RuntimeException {

    /**
     * Constructor que crea la excepción con un mensaje estándar que incluye el ID de la historia no encontrada.
     * @param id el identificador de la historia que no fue encontrada
     */    
    public HistoriaNotFoundException(Long id) {
        super("Historia no encontrada con id: " + id);
    }

    /**
     * Constructor que crea la excepción con un mensaje personalizado.
     * @param message mensaje detallado de la excepción
     */
    public HistoriaNotFoundException(String message) {
        super(message);
    }
}