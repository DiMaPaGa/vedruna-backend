package com.vedruna.vedruna_backend.exceptions;

/**
 * Excepción personalizada que indica que un usuario con un ID específico
 * no fue encontrado en el sistema.
 */
public class UsuarioNotFoundException extends RuntimeException {

     /**
     * Constructor que crea la excepción con un mensaje específico indicando
     * que el usuario con el ID dado no existe.
     *
     * @param userId ID del usuario que no fue encontrado
     */
    public UsuarioNotFoundException(String userId) {
        super("Usuario con ID Google " + userId + " no encontrado.");
    }
    
}
