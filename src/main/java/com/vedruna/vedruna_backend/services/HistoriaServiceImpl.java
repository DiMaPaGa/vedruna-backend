package com.vedruna.vedruna_backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.exceptions.HistoriaNotFoundException;
import com.vedruna.vedruna_backend.exceptions.UsuarioNotFoundException;
import com.vedruna.vedruna_backend.mappers.HistoriaMapper;
import com.vedruna.vedruna_backend.persistance.models.Historia;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.HistoriaRepository;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

@Service
public class HistoriaServiceImpl implements HistoriaService {
    
    @Autowired
    private HistoriaRepository historiaRepository;

    @Autowired
    private HistoriaMapper historiaMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
@Transactional
public HistoriaDTO guardarHistoria(HistoriaDTO historiaDTO) {
    Historia historia = historiaMapper.toEntity(historiaDTO);

    // Establecer autor
    Usuario autor = usuarioRepository.findByUserId(historiaDTO.getUserId())
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con userId: " + historiaDTO.getUserId()));

    historia.setAutor(autor);
    historia.setCreatedAt(LocalDateTime.now());

    Historia historiaGuardada = historiaRepository.save(historia);
    return historiaMapper.toDTO(historiaGuardada);
}

    @Override
    @Transactional(readOnly = true)
    public List<HistoriaDTO> obtenerHistoriasNoExpiradas(LocalDateTime currentDate) {
        return historiaRepository.findByExpiraEnAfter(currentDate)
                .stream()
                .map(historiaMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistoriaDTO> obtenerHistoriaPorId(Long id) {
        return historiaRepository.findById(id)
                .map(historiaMapper::toDTO);
    }

    @Override
    @Transactional
    public void eliminarHistoria(Long id) {
        Historia historia = historiaRepository.findById(id)
                .orElseThrow(() -> new HistoriaNotFoundException("Historia no encontrada con ID: " + id));
        historiaRepository.delete(historia);
    }

    @Override
@Transactional(readOnly = true)
public List<HistoriaDTO> obtenerHistoriasPorUserId(String userId) {
    return historiaRepository.findByAutor_UserId(userId)
            .stream()
            .map(historiaMapper::toDTO)
            .toList();
}
}
