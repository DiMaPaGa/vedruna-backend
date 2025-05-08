package com.vedruna.vedruna_backend.exceptions;

public class HistoriaNotFoundException extends RuntimeException {
    
    public HistoriaNotFoundException(Long id) {
        super("Historia no encontrada con id: " + id);
    }

    public HistoriaNotFoundException(String message) {
        super(message);
    }
}