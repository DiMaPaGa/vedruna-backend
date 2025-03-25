package com.vedruna.vedruna_backend.exceptions;

public class LikeAlreadyExistsException extends RuntimeException{
    public LikeAlreadyExistsException(String message) {
        super(message);
    }
    
}
