package com.vedruna.vedruna_backend.services;



import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.vedruna.vedruna_backend.dto.DispositivoDTO;

import com.vedruna.vedruna_backend.mappers.DispositivoMapper;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.models.UsuarioDispositivo;
import com.vedruna.vedruna_backend.persistance.repositories.DispositivoRepository;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioDispositivoRepository;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

/**
 * Implementación del servicio para la gestión de dispositivos.
 */
@Service
public class DispositivoServiceImpl implements DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DispositivoMapper dispositivoMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDispositivoRepository usuarioDispositivoRepository;

    /**
     * Obtiene un dispositivo por su ID.
     *
     * @param id ID del dispositivo.
     * @return Entidad Dispositivo asociada al ID.
     * @throws RuntimeException si no se encuentra el dispositivo con el ID indicado.
     */
    @Override
    public Dispositivo obtenerPorId(Long id) {
        return dispositivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispositivo no encontrado con id: " + id));  // O cualquier excepción personalizada
    }

    /**
     * Obtiene un dispositivo por su Expo Push ID o, si no existe, lo crea y lo asocia al usuario dado.
     *
     * @param expoPushId el identificador único de Expo Push del dispositivo.
     * @param userId el identificador único del usuario al que se asociará el dispositivo.
     * @return un DTO del dispositivo obtenido o creado.
     * @throws RuntimeException si no se encuentra el usuario con el userId dado o si hay problemas de persistencia.
     */
    @Override
    @Transactional
    public DispositivoDTO obtenerODarDeAlta(String expoPushId, String userId) {
        Dispositivo dispositivo;

    try {
        dispositivo = dispositivoRepository.findByExpoPushId(expoPushId)
            .orElseGet(() -> {
                Dispositivo nuevo = new Dispositivo();
                nuevo.setExpoPushId(expoPushId);
                return dispositivoRepository.save(nuevo);
            });
    } catch (DataIntegrityViolationException e) {
        // Si ya existía otro dispositivo con el mismo expoPushId, lo recuperamos
        dispositivo = dispositivoRepository.findByExpoPushId(expoPushId)
            .orElseThrow(() -> new RuntimeException("Error al recuperar dispositivo ya existente con expoPushId: " + expoPushId));
    }

    Usuario usuario = usuarioRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con userId: " + userId));

    boolean yaExisteRelacion = usuarioDispositivoRepository
        .findByUsuarioIdAndDispositivoId(usuario.getId(), dispositivo.getId())
        .isPresent();

    if (!yaExisteRelacion) {
        UsuarioDispositivo nuevaRelacion = new UsuarioDispositivo();
        nuevaRelacion.setUsuario(usuario);
        nuevaRelacion.setDispositivo(dispositivo);
        nuevaRelacion.setCreatedAt(LocalDateTime.now());
        usuarioDispositivoRepository.save(nuevaRelacion);
    }

    return dispositivoMapper.toDTO(dispositivo);
    }
}


    
