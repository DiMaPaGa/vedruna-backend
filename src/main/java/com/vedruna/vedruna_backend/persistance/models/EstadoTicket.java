package com.vedruna.vedruna_backend.persistance.models;

/**
 * Enumeración que representa los posibles estados de un ticket de soporte o incidencia.
 */
public enum EstadoTicket {
    /**
     * El ticket está en proceso o siendo atendido.
     */
    EN_TRAMITE,

    /**
     * El ticket ha sido solucionado satisfactoriamente.
     */
    SOLUCIONADO,

    /**
     * El ticket ha sido denegado o rechazado.
     */
    DENEGADO
}