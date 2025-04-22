package com.vedruna.vedruna_backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;

@Service
public interface UsuarioService {
// Buscar un usuario por su userId (Google UID)
UsuarioDTO obtenerUsuarioPorGoogleId(String userId) throws UsuarioNotFoundException;

// Crear un nuevo usuario
UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

UsuarioDTO actualizarImagenPerfil(String userId, String nuevaImagen) throws UsuarioNotFoundException;

Page<UsuarioDTO> obtenerUsuariosSugeridos(String userId, Pageable pageable);

}
