package com.vedruna.vedruna_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase de configuración para habilitar la programación de tareas (scheduling) en Spring.
 * 
 * La anotación @EnableScheduling permite que los métodos anotados con @Scheduled
 * en cualquier bean de Spring se ejecuten según la configuración definida.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
    // Esta clase está vacía porque solo se necesita la anotación para habilitar el scheduling
}
