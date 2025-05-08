package com.vedruna.vedruna_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.dto.UsuarioDispositivoDTO;
import com.vedruna.vedruna_backend.mappers.DispositivoMapper;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.services.DispositivoService;
import com.vedruna.vedruna_backend.services.UsuarioDispositivoService;
import com.vedruna.vedruna_backend.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios-dispositivos")
public class UsuarioDispositivoController {

    @Autowired
    private UsuarioDispositivoService usuarioDispositivoService;  // Inyectamos el servicio de la relación
    @Autowired
    private UsuarioService usuarioService;  // Inyectamos el servicio de Usuario
    @Autowired
    private DispositivoService dispositivoService;  // Inyectamos el servicio

    // Asociar Usuario y Dispositivo
    @PostMapping("/asociar")
    public ResponseEntity<String> asociarUsuarioDispositivo(@RequestBody UsuarioDispositivoDTO dto) {
        // Obtiene el usuario por su userId (supongo que tienes un servicio para eso)
        Usuario usuario = usuarioService.obtenerPorUserId(dto.getUserId());

        // Obtiene el dispositivo por su id
        Dispositivo dispositivo = dispositivoService.obtenerPorId(dto.getDispositivoId());

        // Guarda la relación en la base de datos
        usuarioDispositivoService.guardarRelacionUsuarioDispositivo(usuario, dispositivo);
        
        return ResponseEntity.ok("Asociación registrada correctamente");
    }

    // Obtener la relación Usuario-Dispositivo
    @GetMapping("/obtener/{usuarioId}/{dispositivoId}")
    public ResponseEntity<UsuarioDispositivoDTO> obtenerRelacionUsuarioDispositivo(
            @PathVariable Long usuarioId, @PathVariable Long dispositivoId) {
        
        UsuarioDispositivoDTO dto = usuarioDispositivoService.obtenerRelacionUsuarioDispositivo(usuarioId, dispositivoId);
        return ResponseEntity.ok(dto);
    }
    
}
