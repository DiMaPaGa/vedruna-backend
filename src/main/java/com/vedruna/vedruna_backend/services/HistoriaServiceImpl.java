package com.vedruna.vedruna_backend.services;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.dto.HistoriaRequestDTO;
import com.vedruna.vedruna_backend.exceptions.AccessDeniedException;
import com.vedruna.vedruna_backend.exceptions.HistoriaNotFoundException;
import com.vedruna.vedruna_backend.mappers.HistoriaMapper;
import com.vedruna.vedruna_backend.persistance.models.Historia;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.repositories.HistoriaRepository;
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class HistoriaServiceImpl implements HistoriaService {

    private static final Logger logger = LoggerFactory.getLogger(HistoriaServiceImpl.class);
    
    @Autowired
    private HistoriaRepository historiaRepository;

    @Autowired
    private HistoriaMapper historiaMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public HistoriaDTO createHistoria(HistoriaRequestDTO requestDTO) {
        
        Usuario autor = usuarioRepository.findByUserId(requestDTO.getUsuarioId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Agregar un log para verificar que el usuario fue encontrado correctamente
        logger.info("Usuario encontrado: {}", autor);

        Historia historia = historiaMapper.toEntity(requestDTO, autor);
        Historia saved = historiaRepository.save(historia);

        // Agregar un log para verificar que la historia se guardó correctamente
        logger.info("Historia guardada: {}", saved);

        return historiaMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public void deleteHistoria(Long historiaId, String userGoogleId) {
        Historia historia = historiaRepository.findById(historiaId)
            .orElseThrow(() -> new HistoriaNotFoundException("Historia no encontrada con id: " + historiaId));

        String autorUserId = historia.getAutor().getUserId();

        if (!autorUserId.equals(userGoogleId)) {
            throw new AccessDeniedException("No tienes permiso para eliminar esta historia");
        }

        historiaRepository.delete(historia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoriaDTO> getAllHistorias() {
        
        return historiaRepository.findAll().stream()
            .filter(historia -> historia.getExpiraEn().isAfter(LocalDateTime.now()))
            .map(historiaMapper::toDTO)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HistoriaDTO getHistoriaById(Long historiaId) {
        return historiaRepository.findById(historiaId)
            .map(historiaMapper::toDTO)
            .orElseThrow(() -> new HistoriaNotFoundException("Historia no encontrada con id: " + historiaId));
    }
}

