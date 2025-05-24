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


/**
 * Implementación de la interfaz HistoriaService.
 */
@Service
public class HistoriaServiceImpl implements HistoriaService {

    private static final Logger logger = LoggerFactory.getLogger(HistoriaServiceImpl.class);
    
    @Autowired
    private HistoriaRepository historiaRepository;

    @Autowired
    private HistoriaMapper historiaMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    /**
     * Crea una historia con las imágenes y descripciones proporcionadas y la relaciona con el usuario
     * que la crea.
     *
     * Verifica que el usuario que se proporciona exista en la base de datos y lanza una excepción
     * si no lo encuentra.
     *
     * @param requestDTO datos necesarios de la historia a crear
     * @return historia creada
     * @throws RuntimeException si el usuario no se encuentra
     */
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

    
    /**
     * Elimina una historia con el ID proporcionado y el ID de usuario Google.
     * 
     * Verifica que el usuario que se proporciona sea el autor de la historia y lanza una excepción
     * si no lo es.
     * 
     * @param historiaId el ID de la historia a eliminar.
     * @param userGoogleId el ID de usuario Google que realiza la eliminación.
     * @throws HistoriaNotFoundException si la historia no se encuentra.
     * @throws AccessDeniedException si el usuario no es el autor de la historia.
     */
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

    /**
     * Obtiene todas las historias activas (no expiradas).
     *
     * @return Lista de DTOs de historias activas.
     */
    @Override
    @Transactional(readOnly = true)
    public List<HistoriaDTO> getAllHistorias() {
        
        return historiaRepository.findAll().stream()
            .filter(historia -> historia.getExpiraEn().isAfter(LocalDateTime.now()))
            .map(historiaMapper::toDTO)
            .toList();
    }

    /**
     * Obtiene una historia por su ID.
     *
     * @param historiaId el ID de la historia a obtener.
     * @return el DTO de la historia obtenida.
     * @throws HistoriaNotFoundException si la historia no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public HistoriaDTO getHistoriaById(Long historiaId) {
        return historiaRepository.findById(historiaId)
            .map(historiaMapper::toDTO)
            .orElseThrow(() -> new HistoriaNotFoundException("Historia no encontrada con id: " + historiaId));
    }
}

