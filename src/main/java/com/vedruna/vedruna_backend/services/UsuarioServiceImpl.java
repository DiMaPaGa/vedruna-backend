package com.vedruna.vedruna_backend.services;

import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO obtenerUsuarioPorGoogleId(String userId) throws UsuarioNotFoundException {
        // Buscar el usuario por su user_id
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUserId(userId);

        // Si el usuario no existe, lanzar excepción
    if (optionalUsuario.isEmpty()) {
        throw new UsuarioNotFoundException("Usuario no encontrado con el ID: " + userId);
    }

    // Obtener el usuario del Optional
    Usuario usuario = optionalUsuario.get();

    // Convertir la entidad Usuario a UsuarioDTO
    return usuarioMapper.toDTO(usuario);
}

    @Override
    @Transactional
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        // Verificar si el usuario ya existe
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUserId(usuarioDTO.getUserId());
        
        if (usuarioExistente.isPresent()) {
            return usuarioMapper.toDTO(usuarioExistente.get());
        }
        
        // Si no existe, crear el usuario
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        
        return usuarioMapper.toDTO(savedUsuario);
    }
}
