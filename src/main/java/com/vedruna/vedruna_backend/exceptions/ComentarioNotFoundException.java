package com.vedruna.vedruna_backend.exceptions;

public class ComentarioNotFoundException extends RuntimeException {
    public ComentarioNotFoundException(String message) {
        super(message);  
    }   
}
