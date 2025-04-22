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
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    // Obtener un usuario por Google ID
    @GetMapping("/{userId}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorGoogleId(@PathVariable String userId) throws UsuarioNotFoundException {
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorGoogleId(userId);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UsuarioDTO> actualizarImagenPerfil(@PathVariable String userId, @RequestBody Map<String, String> body) throws UsuarioNotFoundException {
    String nuevaImagen = body.get("profile_picture");
    UsuarioDTO actualizado = usuarioService.actualizarImagenPerfil(userId, nuevaImagen);
    return new ResponseEntity<>(actualizado, HttpStatus.OK);
}

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        logger.info("Datos recibidos en el POST /api/usuarios: {}", usuarioDTO);

        try{
            //Verificar si el usuario ya existe
            UsuarioDTO usuarioExistente = usuarioService.obtenerUsuarioPorGoogleId(usuarioDTO.getUserId());
            logger.info("El usuario ya existe: en la base de datos, no se registrará nuevamente.");

            //Devolver el usuario existente con el código 200
            return new ResponseEntity<>(usuarioExistente, HttpStatus.OK);
        } catch (UsuarioNotFoundException e) {
            logger.info("El usuario no existe en la base de datos, se registrará.");

            //Crear el usuario
            UsuarioDTO creadoUsuario = usuarioService.crearUsuario(usuarioDTO);
            return new ResponseEntity<>(creadoUsuario, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{userId}/sugerencias")
    public ResponseEntity<Page<UsuarioDTO>> sugerenciasUsuarios(
            @PathVariable String userId,
            Pageable pageable) {

        Page<UsuarioDTO> sugerencias = usuarioService.obtenerUsuariosSugeridos(userId, pageable);
        return new ResponseEntity<>(sugerencias, HttpStatus.OK);
    }
}