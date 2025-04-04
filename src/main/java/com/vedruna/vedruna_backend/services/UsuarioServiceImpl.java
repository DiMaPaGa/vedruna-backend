package com.vedruna.vedruna_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.mappers.UsuarioMapper;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Buscar un usuario por su userId (Google UID)
    @Override
    @Transactional(readOnly = true) // Esto asegura que esta operación no realice modificaciones en la base de datos
    public UsuarioDTO obtenerUsuarioPorGoogleId(String userId) throws UsuarioNotFoundException {
        // Buscar el usuario por su userId
        Usuario usuario = usuarioRepository.findByUserId(userId);

        // Si no se encuentra el usuario, lanzamos la excepción
        if (usuario == null) {
            throw new UsuarioNotFoundException("Usuario no encontrado con el ID: " + userId);
        }

        // Mapeamos el modelo de Usuario a UsuarioDTO
        return usuarioMapper.toDTO(usuario);
    }

    // Crear un nuevo usuario
    @Override
    @Transactional // La transacción es necesaria para guardar el usuario en la base de datos
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        // Convertimos el DTO a la entidad Usuario
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        // Guardamos el usuario en la base de datos
        Usuario savedUsuario = usuarioRepository.save(usuario);

        // Devolvemos el DTO del usuario guardado
        return usuarioMapper.toDTO(savedUsuario);
    }
}
