package com.vedruna.vedruna_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.UsuarioDispositivoDTO;
import com.vedruna.vedruna_backend.exceptions.UsuarioDispositivoException;
import com.vedruna.vedruna_backend.mappers.UsuarioDispositivoMapper;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.models.UsuarioDispositivo;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioDispositivoRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Implementación de la interfaz UsuarioDispositivoService.
 */
@Service
public class UsuarioDispositivoServiceImpl implements UsuarioDispositivoService {
    @Autowired
    private UsuarioDispositivoRepository usuarioDispositivoRepository; // Repositorio de la entidad

    @Autowired
    private UsuarioService usuarioService;   // Para obtener el Usuario
    @Autowired
    private DispositivoService dispositivoService;  // Para obtener el Dispositivo
    @Autowired
    private UsuarioDispositivoMapper usuarioDispositivoMapper; 

    /**
     * Guarda una nueva relación entre un usuario y un dispositivo si no existe previamente.
     * 
     * @param usuario El usuario a asociar con el dispositivo.
     * @param dispositivo El dispositivo a asociar con el usuario.
     * @throws UsuarioDispositivoException si la relación ya existe.
     */
    @Override
    public void guardarRelacionUsuarioDispositivo(Usuario usuario, Dispositivo dispositivo) {
        if (existeRelacion(usuario, dispositivo)) {
            throw new UsuarioDispositivoException("La relación usuario-dispositivo ya existe.");
        }

        UsuarioDispositivo usuarioDispositivo = new UsuarioDispositivo(usuario, dispositivo);
        usuarioDispositivoRepository.save(usuarioDispositivo);
    }

    /**
     * Obtiene una relación entre un usuario y un dispositivo.
     * 
     * @param usuarioId    ID del usuario.
     * @param dispositivoId ID del dispositivo.
     * @return DTO con la relación usuario-dispositivo si existe.
     * @throws EntityNotFoundException si no se encuentra la relación.
     */
     @Override
    public UsuarioDispositivoDTO obtenerRelacionUsuarioDispositivo(Long usuarioId, Long dispositivoId) {
        
        Optional<UsuarioDispositivo> usuarioDispositivoOptional = usuarioDispositivoRepository
            .findByUsuarioIdAndDispositivoId(usuarioId, dispositivoId);
        
        if (usuarioDispositivoOptional.isPresent()) {
            return usuarioDispositivoMapper.toDto(usuarioDispositivoOptional.get());
        } else {
            throw new EntityNotFoundException("La relación usuario-dispositivo no existe.");
        }
    }

    /**
     * Verifica si ya existe una relación entre un usuario y un dispositivo.
     * 
     * @param usuario    Entidad Usuario.
     * @param dispositivo Entidad Dispositivo.
     * @return true si la relación existe, false en caso contrario.
     */
    private boolean existeRelacion(Usuario usuario, Dispositivo dispositivo) {
        // Verificamos si ya existe una relación entre este usuario y dispositivo
        Optional<UsuarioDispositivo> usuarioDispositivo = usuarioDispositivoRepository
            .findByUsuarioIdAndDispositivoId(usuario.getId(), dispositivo.getId());
        return usuarioDispositivo.isPresent();
    }

    /**
     * Obtiene la lista de tokens Expo Push IDs asociados a un usuario dado su userId (campo String único).
     * 
     * @param userId User ID único del usuario.
     * @return Lista de Expo Push IDs asociados a los dispositivos del usuario.
     */
    @Override
    public List<String> obtenerTokensPorUserId(String userId) {
    return usuarioDispositivoRepository.findExpoPushIdsByUserId(userId);
}
}
