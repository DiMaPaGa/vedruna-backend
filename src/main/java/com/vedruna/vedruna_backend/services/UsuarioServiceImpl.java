package com.vedruna.vedruna_backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.mappers.UsuarioMapper;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

/**
 * Implementación de la interfaz UsuarioService.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    /**
     * Obtiene un usuario por su ID de Google (userId). Lanza una excepción
     * {@link UsuarioNotFoundException} si el usuario no se encuentra.
     * 
     * @param userId ID de Google del usuario a buscar
     * @return DTO con los datos del usuario encontrado
     * @throws UsuarioNotFoundException si el usuario no se encuentra
     */
    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO obtenerUsuarioPorGoogleId(String userId) throws UsuarioNotFoundException {
        
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUserId(userId);

        
    if (optionalUsuario.isEmpty()) {
        throw new UsuarioNotFoundException("Usuario no encontrado con el ID: " + userId);
    }

    Usuario usuario = optionalUsuario.get();
    return usuarioMapper.toDTO(usuario);
}

/**
 * Crea un nuevo usuario en el sistema. Si el usuario ya existe, devuelve los
 * datos del usuario existente. Si no existe, se crea y guarda un nuevo usuario
 * en la base de datos y se devuelve su información.
 * 
 * @param usuarioDTO Datos del usuario a crear
 * @return DTO con los datos del usuario creado o existente
 */

    @Override
    @Transactional
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUserId(usuarioDTO.getUserId());
        
        if (usuarioExistente.isPresent()) {
            return usuarioMapper.toDTO(usuarioExistente.get());
        }
        
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        
        return usuarioMapper.toDTO(savedUsuario);
    }

/**
 * Actualiza la imagen de perfil de un usuario identificado por su ID de Google.
 * 
 * @param userId ID de Google del usuario cuya imagen de perfil se va a actualizar
 * @param nuevaImagen URL de la nueva imagen de perfil
 * @return DTO con los datos del usuario actualizado
 * @throws UsuarioNotFoundException si el usuario con el ID proporcionado no se encuentra
 */
    @Override
    @Transactional
    public UsuarioDTO actualizarImagenPerfil(String userId, String nuevaImagen) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findByUserId(userId)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
    
        usuario.setProfileImageUrl(nuevaImagen);
        usuarioRepository.save(usuario);
    
        return usuarioMapper.toDTO(usuario);
    }

    /**
     * Obtiene una página de sugerencias de usuarios que no ha seguido
     * un usuario concreto. La búsqueda se hace en base al nombre de los usuarios.
     * 
     * @param userId ID del usuario que hace la búsqueda
     * @param pageable Información de paginación
     * @return Página de DTO de usuarios sugeridos
     */
    @Override
    @Transactional (readOnly = true)
    public Page<UsuarioDTO> obtenerUsuariosSugeridos(String userId, Pageable pageable) {
        Page<Usuario> sugerencias = usuarioRepository.encontrarUsuariosNoSeguidos(userId, pageable);
        return sugerencias.map(usuarioMapper::toDTO);
    }

    /**
     * Busca usuarios por su nombre.
     * 
     * @param nombre nombre parcial a buscar
     * @param pageable Información de paginación
     * @return Página de DTO de usuarios encontrados
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioDTO> buscarUsuariosPorNombre(String nombre, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.buscarPorNombre(nombre, pageable);
        return usuarios.map(usuarioMapper::toDTO);
    }

    /**
     * Obtiene un usuario por su ID de Google (userId). Lanza una excepción
     * {@link UsuarioNotFoundException} si el usuario no se encuentra.
     * 
     * @param userId ID de Google del usuario a buscar
     * @return Entidad del usuario encontrado
     * @throws UsuarioNotFoundException si el usuario no se encuentra
     */
    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerPorUserId(String userId) {
        return usuarioRepository.findByUserId(userId)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario con userId " + userId + " no encontrado."));
    }
}
