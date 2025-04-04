package com.vedruna.vedruna_backend.exceptions;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String userId) {
        super("Usuario con ID Google " + userId + " no encontrado.");
    }
    
}
