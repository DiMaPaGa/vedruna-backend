package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.PublicacionDTO;
import com.vedruna.vedruna_backend.exceptions.ResourceNotFoundException;
import com.vedruna.vedruna_backend.mappers.PublicacionMapper;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;
import com.vedruna.vedruna_backend.persistance.repositories.PublicacionRepository;

@Service
public class PublicacionServiceImpl implements PublicacionService {
    private static final Logger logger = LoggerFactory.getLogger(PublicacionServiceImpl.class);

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private PublicacionMapper publicacionMapper;


    @Transactional
@Override
public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
    logger.info("Datos recibidos: {}", publicacionDTO);

    Publicacion publicacion = publicacionMapper.toEntity(publicacionDTO);
    logger.info("Entidad mapeada: {}", publicacion);

    publicacion = publicacionRepository.save(publicacion);
    logger.info("Publicación guardada: {}", publicacion);

    PublicacionDTO createdDTO = publicacionMapper.toDTO(publicacion);
    logger.info("DTO de la publicación creada: {}", createdDTO);

    return createdDTO;
}


    @Transactional(readOnly = true)
    @Override
    public PublicacionDTO obtenerPublicacionPorId(Long id) throws ResourceNotFoundException {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicación no encontrada"));
        return publicacionMapper.toDTO(publicacion);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PublicacionDTO> obtenerPublicacionesPorUsuario(String userId) {
        List<Publicacion> publicaciones = publicacionRepository.findByUserId(userId);
        return publicaciones.stream()
                .map(publicacionMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PublicacionDTO> obtenerPublicacionesConLikePorUsuario(String userId) {
        List<Publicacion> publicaciones = publicacionRepository.findByLikes_LikeId_UserId(userId);
        return publicaciones.stream()
                .map(publicacionMapper::toDTO)
                .toList();
    }
    
}
