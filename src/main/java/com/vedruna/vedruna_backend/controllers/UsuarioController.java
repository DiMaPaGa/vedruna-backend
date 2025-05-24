package com.vedruna.vedruna_backend.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener un usuario por su Google ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                     content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorGoogleId(@PathVariable String userId) throws UsuarioNotFoundException {
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorGoogleId(userId);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar imagen de perfil de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagen actualizada correctamente",
                     content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<UsuarioDTO> actualizarImagenPerfil(
        @PathVariable String userId, 
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Nuevo URL de imagen de perfil",
            required = true,
            content = @Content(schema = @Schema(example = "{\"profile_picture\": \"http://example.com/image.jpg\"}"))
        )
        @RequestBody Map<String, String> body) throws UsuarioNotFoundException {

        String nuevaImagen = body.get("profile_picture");
        UsuarioDTO actualizado = usuarioService.actualizarImagenPerfil(userId, nuevaImagen);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                     content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "200", description = "El usuario ya existía, se devuelve el existente")
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del usuario a crear",
            required = true,
            content = @Content(schema = @Schema(implementation = UsuarioDTO.class))
        )
        @RequestBody UsuarioDTO usuarioDTO) {
            
        logger.info("Datos recibidos en el POST /api/usuarios: {}", usuarioDTO);

        try{
            //Verificar si el usuario ya existe
            UsuarioDTO usuarioExistente = usuarioService.obtenerUsuarioPorGoogleId(usuarioDTO.getUserId());
            logger.info("El usuario ya existe: en la base de datos, no se registrará nuevamente.");

            //Devolver el usuario existente con el código 200
            return new ResponseEntity<>(usuarioExistente, HttpStatus.OK);
        } catch (UsuarioNotFoundException e) {
            logger.info("El usuario no existe en la base de datos, se registrará.");
            UsuarioDTO creadoUsuario = usuarioService.crearUsuario(usuarioDTO);
            return new ResponseEntity<>(creadoUsuario, HttpStatus.CREATED);
        }
    }


    @Operation(summary = "Obtener sugerencias de usuarios para seguir")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sugerencias encontradas",
                     content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    })
    @GetMapping("/{userId}/sugerencias")
    public ResponseEntity<Page<UsuarioDTO>> sugerenciasUsuarios(
            @PathVariable String userId,
            Pageable pageable) {

        Page<UsuarioDTO> sugerencias = usuarioService.obtenerUsuariosSugeridos(userId, pageable);
        return new ResponseEntity<>(sugerencias, HttpStatus.OK);
    }

    @Operation(summary = "Buscar usuarios por nombre")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios encontrados",
                     content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    })
    @GetMapping("/buscar")
    public ResponseEntity<Page<UsuarioDTO>> buscarUsuarios(
        @RequestParam(defaultValue = "") String nombre,
        Pageable pageable) {

    Page<UsuarioDTO> usuarios = usuarioService.buscarUsuariosPorNombre(nombre, pageable);
    return new ResponseEntity<>(usuarios, HttpStatus.OK);
}
}