package com.vedruna.vedruna_backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.UsuarioDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.persistance.models.Usuario;

/**
 * Interfaz de servicio para la gestión de usuarios.
 */
@Service
public interface UsuarioService {

/**
 * Obtiene un usuario por su ID de Google.
 * @param userId ID de Google del usuario
 * @return Usuario encontrado
 * @throws UsuarioNotFoundException si el usuario no existe
 */
UsuarioDTO obtenerUsuarioPorGoogleId(String userId) throws UsuarioNotFoundException;

/**
 * Crea un nuevo usuario.
 * @param usuarioDTO Datos del nuevo usuario
 * @return  Usuario creado
 */
UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

/**
 * Actualiza la imagen de perfil de un usuario.
 * @param userId ID de Google del usuario
 * @param nuevaImagen URL de la nueva imagen de perfil
 * @return Usuario actualizado
 * @throws UsuarioNotFoundException si el usuario no existe
 */
UsuarioDTO actualizarImagenPerfil(String userId, String nuevaImagen) throws UsuarioNotFoundException;

/**
 * Obtiene los usuarios sugeridos para un usuario dado.
 * @param userId ID de Google del usuario
 * @param pageable Información de paginación
 * @return Usuarios sugeridos paginados
 */
Page<UsuarioDTO> obtenerUsuariosSugeridos(String userId, Pageable pageable);

/**
 * Busca usuarios por su nombre.
 * @param nombre Nombre del usuario
 * @param pageable Información de paginación
 * @return Usuarios encontrados paginados
 */
Page<UsuarioDTO> buscarUsuariosPorNombre(String nombre, Pageable pageable);

/**
 * Obtiene un usuario por su ID de Google.
 * @param userId ID de Google del usuario
 * @return Usuario encontrado
 */
Usuario obtenerPorUserId(String userId);

}
