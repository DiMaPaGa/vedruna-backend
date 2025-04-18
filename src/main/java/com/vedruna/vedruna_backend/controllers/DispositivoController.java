package com.vedruna.vedruna_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/id/{expoPushId}")
    public ResponseEntity<DispositivoDTO> obtenerDispositivoPorId(@PathVariable String expoPushId) {
        // Obtiene el dispositivo utilizando el servicio y lanza una excepción si no se encuentra
        DispositivoDTO dispositivoDTO = dispositivoService.obtenerDispositivoPorId(expoPushId)
                .orElseThrow(() -> new DispositivoNotFoundException("Dispositivo no encontrado con el expoPushId: " + expoPushId));

        return ResponseEntity.ok(dispositivoDTO);
    }


}
