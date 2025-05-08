package com.vedruna.vedruna_backend.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.dto.ExpoPushRequest;
import com.vedruna.vedruna_backend.services.DispositivoService;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

   @PostMapping("/registrar")
   public ResponseEntity<?> registrarODarDeAlta(@RequestBody ExpoPushRequest request) {
    try {
        DispositivoDTO dto = dispositivoService.obtenerODarDeAlta(request.getExpoPushId(), request.getUserId());
        return ResponseEntity.ok(dto);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
    }
}

}
