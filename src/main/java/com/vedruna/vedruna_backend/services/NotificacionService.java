package com.vedruna.vedruna_backend.services;

import java.util.List;

public interface  NotificacionService {

    void enviarNotificacion(List<String> tokens, String titulo, String cuerpo);
    
}
