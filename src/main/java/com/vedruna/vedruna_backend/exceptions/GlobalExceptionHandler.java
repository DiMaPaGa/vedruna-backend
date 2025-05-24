package com.vedruna.vedruna_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para la API REST.
 * Centraliza el tratamiento de las excepciones personalizadas lanzadas por los servicios,
 * devolviendo respuestas HTTP adecuadas con códigos y mensajes específicos.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción UsuarioNotFoundException lanzada cuando un usuario no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción ComentarioNotFoundException lanzada cuando un comentario no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(ComentarioNotFoundException.class)
    public ResponseEntity<String> handleComentarioNotFound(ComentarioNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción PublicacionNotFoundException lanzada cuando una publicación no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(PublicacionNotFoundException.class)
    public ResponseEntity<String> handlePublicacionNotFound(PublicacionNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción DispositivoNotFoundException lanzada cuando un dispositivo no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(DispositivoNotFoundException.class)
    public ResponseEntity<String> handleDispositivoNotFound(DispositivoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción HistoriaNotFoundException lanzada cuando una historia no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(HistoriaNotFoundException.class)
    public ResponseEntity<String> handleHistoriaNotFound(HistoriaNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción LikeNotFoundException lanzada cuando un "like" no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(LikeNotFoundException.class)
    public ResponseEntity<String> handleLikeNotFoundException(LikeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción LikeAlreadyExistsException lanzada cuando un "like" ya existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 409 Conflict
     */
    @ExceptionHandler(LikeAlreadyExistsException.class)
    public ResponseEntity<String> handleLikeAlreadyExistsException(LikeAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    
    /**
     * Maneja la excepción SeguidorNotFoundException lanzada cuando un seguidor no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(SeguidorNotFoundException.class)
    public ResponseEntity<String> handleSeguidorNotFoundException(SeguidorNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción TicketNotFoundException lanzada cuando un ticket no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<String> handleTicketNotFoundException(TicketNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción ResourceNotFoundException lanzada cuando un recurso no existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 404 Not Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción SeguimientoExistenteException lanzada cuando un seguimiento ya existe.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 409 Conflict
     */
    @ExceptionHandler(SeguimientoExistenteException.class)
    public ResponseEntity<String> handleSeguimientoExistenteException(SeguimientoExistenteException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    /**
     * Maneja la excepción UsuarioDispositivoException lanzada por errores en asociaciones usuario-dispositivo.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 400 Bad Request
     */
    @ExceptionHandler(UsuarioDispositivoException.class)
    public ResponseEntity<String> handleUsuarioDispositivoException(UsuarioDispositivoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); 
    }

    /**
     * Maneja la excepción AccessDeniedException lanzada cuando se deniega el acceso a un recurso.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje y código 403 Forbidden
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    /**
     * Maneja cualquier otra excepción no controlada.
     * @param ex la excepción lanzada
     * @return ResponseEntity con mensaje genérico y código 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }



   
    
}
