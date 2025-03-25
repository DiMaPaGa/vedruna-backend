package com.vedruna.vedruna_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.DispositivoDTO;
import com.vedruna.vedruna_backend.exceptions.DispositivoNotFoundException;
import com.vedruna.vedruna_backend.mappers.DispositivoMapper;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.repositories.DispositivoRepository;

@Service
public class DispositivoServiceImpl implements DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DispositivoMapper dispositivoMapper;

    @Override
    @Transactional
    public DispositivoDTO guardarDispositivo(DispositivoDTO dispositivoDTO) {
        Dispositivo dispositivo = dispositivoMapper.toEntity(dispositivoDTO);
        dispositivo.setCreatedAt(java.time.LocalDateTime.now()); // Establecer la fecha de creación
        Dispositivo dispositivoGuardado = dispositivoRepository.save(dispositivo);
        return dispositivoMapper.toDTO(dispositivoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DispositivoDTO> obtenerDispositivosPorUserId(String userId) {
        return dispositivoRepository.findByUserId(userId)
                .stream()
                .map(dispositivoMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DispositivoDTO> obtenerDispositivoPorToken(String expoPushToken) {
        return dispositivoRepository.findByExpoPushToken(expoPushToken)
                .map(dispositivoMapper::toDTO);
    }

    @Override
    @Transactional
    public void eliminarDispositivo(String expoPushToken) {
        Dispositivo dispositivo = dispositivoRepository.findByExpoPushToken(expoPushToken)
                .orElseThrow(() -> new DispositivoNotFoundException("Dispositivo con token " + expoPushToken + " no encontrado"));
        dispositivoRepository.delete(dispositivo);
    }
    
}
