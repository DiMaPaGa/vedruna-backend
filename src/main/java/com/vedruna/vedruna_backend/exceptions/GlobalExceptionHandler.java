package com.vedruna.vedruna_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de la excepción UsuarioNotFoundException
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
     // Maneja excepciones de tipo ComentarioNotFoundException
    @ExceptionHandler(ComentarioNotFoundException.class)
    public ResponseEntity<String> handleComentarioNotFound(ComentarioNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Maneja excepciones de tipo PublicacionNotFoundException
    @ExceptionHandler(PublicacionNotFoundException.class)
    public ResponseEntity<String> handlePublicacionNotFound(PublicacionNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Maneja excepciones de tipo DispositivoNotFoundException
    @ExceptionHandler(DispositivoNotFoundException.class)
    public ResponseEntity<String> handleDispositivoNotFound(DispositivoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Maneja excepciones de tipo HistoriaNotFoundException
    @ExceptionHandler(HistoriaNotFoundException.class)
    public ResponseEntity<String> handleHistoriaNotFound(HistoriaNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Maneja excepciones de tipo LikeNotFoundException
    @ExceptionHandler(LikeNotFoundException.class)
    public ResponseEntity<String> handleLikeNotFoundException(LikeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Maneja excepciones de tipo LikeAlreadyExistsException
    @ExceptionHandler(LikeAlreadyExistsException.class)
    public ResponseEntity<String> handleLikeAlreadyExistsException(LikeAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(SeguidorNotFoundException.class)
    public ResponseEntity<String> handleSeguidorNotFoundException(SeguidorNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<String> handleTicketNotFoundException(TicketNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeguimientoExistenteException.class)
    public ResponseEntity<String> handleSeguimientoExistenteException(SeguimientoExistenteException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body(ex.getMessage());
    }

    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }



   
    
}
