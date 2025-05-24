package com.vedruna.vedruna_backend.services;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Implementación del servicio de notificaciones push.
 */
@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final String EXPO_PUSH_URL = "https://exp.host/--/api/v2/push/send";

    /**
     * Envía una notificación push a una lista de tokens de dispositivos.
     * Para cada token, construye el payload y realiza una llamada HTTP POST
     * al servicio de Expo Push Notifications.
     *
     * @param tokens Lista de tokens de dispositivos destino.
     * @param titulo Título de la notificación.
     * @param cuerpo Cuerpo o mensaje de la notificación.
     */
    @Override
    public void enviarNotificacion(List<String> tokens, String titulo, String cuerpo) {
        RestTemplate restTemplate = new RestTemplate();

        for (String token : tokens) {
            Map<String, Object> payload = new HashMap<>();
            payload.put("to", token);
            payload.put("title", titulo);
            payload.put("body", cuerpo);
            payload.put("sound", "default");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

            try {
                restTemplate.postForEntity(EXPO_PUSH_URL, entity, String.class);
            } catch (Exception e) {
                System.err.println("Error enviando notificación a " + token + ": " + e.getMessage());
            }
        }
    }
}
