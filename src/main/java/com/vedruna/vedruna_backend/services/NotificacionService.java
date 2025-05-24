package com.vedruna.vedruna_backend.services;

import java.util.List;

/**
 * Servicio para el envío de notificaciones push a dispositivos.
 */
public interface  NotificacionService {

    /**
     * Envía una notificación push a una lista de tokens de dispositivos.
     *
     * @param tokens Lista de tokens de dispositivos destino.
     * @param titulo Título de la notificación.
     * @param cuerpo Cuerpo o mensaje de la notificación.
     */
    void enviarNotificacion(List<String> tokens, String titulo, String cuerpo);
    
}
