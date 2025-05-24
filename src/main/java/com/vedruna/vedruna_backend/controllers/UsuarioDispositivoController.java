package com.vedruna.vedruna_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.UsuarioDispositivoDTO;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.services.DispositivoService;
import com.vedruna.vedruna_backend.services.UsuarioDispositivoService;
import com.vedruna.vedruna_backend.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/usuarios-dispositivos")
public class UsuarioDispositivoController {

    @Autowired
    private UsuarioDispositivoService usuarioDispositivoService;  // Inyectamos el servicio de la relación
    @Autowired
    private UsuarioService usuarioService;  // Inyectamos el servicio de Usuario
    @Autowired
    private DispositivoService dispositivoService;  // Inyectamos el servicio

    @Operation(
        summary = "Asociar un usuario a un dispositivo",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para asociar un usuario con un dispositivo",
            required = true,
            content = @Content(schema = @Schema(implementation = UsuarioDispositivoDTO.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Asociación registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario o dispositivo no encontrado")
        }
    )
    @PostMapping("/asociar")
    public ResponseEntity<String> asociarUsuarioDispositivo(@RequestBody UsuarioDispositivoDTO dto) {
        
        Usuario usuario = usuarioService.obtenerPorUserId(dto.getUserId());
        Dispositivo dispositivo = dispositivoService.obtenerPorId(dto.getDispositivoId());
        usuarioDispositivoService.guardarRelacionUsuarioDispositivo(usuario, dispositivo);
        return ResponseEntity.ok("Asociación registrada correctamente");
    }

    @Operation(
        summary = "Obtener una asociación entre usuario y dispositivo",
        responses = {
            @ApiResponse(responseCode = "200", description = "Asociación encontrada",
                        content = @Content(schema = @Schema(implementation = UsuarioDispositivoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Asociación no encontrada")
        }
    )
    @GetMapping("/obtener/{usuarioId}/{dispositivoId}")
    public ResponseEntity<UsuarioDispositivoDTO> obtenerRelacionUsuarioDispositivo(
            @PathVariable Long usuarioId, 
            @PathVariable Long dispositivoId) {
        
        UsuarioDispositivoDTO dto = usuarioDispositivoService.obtenerRelacionUsuarioDispositivo(usuarioId, dispositivoId);
        return ResponseEntity.ok(dto);
    }
    
}
