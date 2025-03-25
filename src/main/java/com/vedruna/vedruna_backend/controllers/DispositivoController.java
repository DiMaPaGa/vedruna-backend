package com.vedruna.vedruna_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.exceptions.DispositivoNotFoundException;
import com.vedruna.vedruna_backend.services.DispositivoService;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<DispositivoDTO> guardarDispositivo(@RequestBody DispositivoDTO dispositivoDTO) {
        DispositivoDTO dispositivoGuardado = dispositivoService.guardarDispositivo(dispositivoDTO);
        return ResponseEntity.ok(dispositivoGuardado);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DispositivoDTO>> obtenerDispositivosPorUserId(@PathVariable String userId) {
        List<DispositivoDTO> dispositivos = dispositivoService.obtenerDispositivosPorUserId(userId);
        
        if (dispositivos.isEmpty()) {
            throw new DispositivoNotFoundException("No se encontraron dispositivos para el usuario con ID: " + userId);
        }

        return ResponseEntity.ok(dispositivos);
    }

    @GetMapping("/token/{expoPushToken}")
    public ResponseEntity<DispositivoDTO> obtenerDispositivoPorToken(@PathVariable String expoPushToken) {
        // Obtiene el dispositivo utilizando el servicio y lanza una excepción si no se encuentra
        DispositivoDTO dispositivoDTO = dispositivoService.obtenerDispositivoPorToken(expoPushToken)
                .orElseThrow(() -> new DispositivoNotFoundException("Dispositivo no encontrado con el expoPushToken: " + expoPushToken));

        return ResponseEntity.ok(dispositivoDTO);
    }

    @DeleteMapping("/token/{expoPushToken}")
    public ResponseEntity<Void> eliminarDispositivo(@PathVariable String expoPushToken) {
        // Elimina el dispositivo utilizando el servicio. El servicio ya lanza una excepción si no se encuentra
        dispositivoService.eliminarDispositivo(expoPushToken);
        return ResponseEntity.noContent().build(); // Respuesta 204 cuando el dispositivo es eliminado con éxito
    }
}
