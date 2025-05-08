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

    @Override
    public void guardarRelacionUsuarioDispositivo(Usuario usuario, Dispositivo dispositivo) {
        if (existeRelacion(usuario, dispositivo)) {
            throw new UsuarioDispositivoException("La relación usuario-dispositivo ya existe.");
        }

        UsuarioDispositivo usuarioDispositivo = new UsuarioDispositivo(usuario, dispositivo);
        usuarioDispositivoRepository.save(usuarioDispositivo);
    }

     @Override
    public UsuarioDispositivoDTO obtenerRelacionUsuarioDispositivo(Long usuarioId, Long dispositivoId) {
        // Buscamos la relación en la base de datos
        Optional<UsuarioDispositivo> usuarioDispositivoOptional = usuarioDispositivoRepository
            .findByUsuarioIdAndDispositivoId(usuarioId, dispositivoId);
        
        if (usuarioDispositivoOptional.isPresent()) {
            return usuarioDispositivoMapper.toDto(usuarioDispositivoOptional.get());
        } else {
            throw new EntityNotFoundException("La relación usuario-dispositivo no existe.");
        }
    }

    private boolean existeRelacion(Usuario usuario, Dispositivo dispositivo) {
        // Verificamos si ya existe una relación entre este usuario y dispositivo
        Optional<UsuarioDispositivo> usuarioDispositivo = usuarioDispositivoRepository
            .findByUsuarioIdAndDispositivoId(usuario.getId(), dispositivo.getId());
        return usuarioDispositivo.isPresent();
    }

    @Override
    public List<String> obtenerTokensPorUserId(String userId) {
    return usuarioDispositivoRepository.findExpoPushIdsByUserId(userId);
}
}
