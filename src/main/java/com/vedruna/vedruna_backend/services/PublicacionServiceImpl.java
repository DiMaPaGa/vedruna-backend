package com.vedruna.vedruna_backend.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.PublicacionDTO;
import com.vedruna.vedruna_backend.exceptions.ResourceNotFoundException;
import com.vedruna.vedruna_backend.mappers.PublicacionMapper;
import com.vedruna.vedruna_backend.persistance.models.Privacidad;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;
import com.vedruna.vedruna_backend.persistance.repositories.PublicacionRepository;

@Service
public class PublicacionServiceImpl implements PublicacionService {
    private static final Logger logger = LoggerFactory.getLogger(PublicacionServiceImpl.class);

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private PublicacionMapper publicacionMapper;

    @Autowired
    private SeguidorService seguidorService; 

    @Autowired
    private LikeService likeService;  


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
        List<Publicacion> publicaciones = publicacionRepository.findByAutor_UserId(userId);
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

    // Nuevos métodos para obtener las publicaciones
    @Transactional(readOnly = true)
    @Override
    public List<PublicacionDTO> obtenerTodasLasPublicaciones(String userId) {
        List<PublicacionDTO> publicaciones = new ArrayList<>();

        // Obtener publicaciones públicas
        List<Publicacion> publicacionesPublicas = publicacionRepository.findByPrivacidad(Privacidad.PUBLICA);
        publicacionesPublicas.forEach(publicacion -> {
            // Obtener los likes de la publicación
            List<LikeDTO> likes = likeService.obtenerLikesDePublicacion(publicacion.getId());
            // Mapear la publicación a DTO
            PublicacionDTO dto = publicacionMapper.toDTO(publicacion);
            // Añadir los likes al DTO
            dto.setLikes(likes);
            publicaciones.add(dto);
        });

        // Obtener las publicaciones privadas de los usuarios seguidos
        List<Publicacion> publicacionesPrivadas = publicacionRepository.findByPrivacidad(Privacidad.PRIVADA);
        for (Publicacion publicacion : publicacionesPrivadas) {
            // Si el usuario es seguidor del autor de la publicación, añadirla
            if (seguidorService.esSeguidor(userId, publicacion.getAutor().getUserId()) || publicacion.getAutor().getUserId().equals(userId)) {
                List<LikeDTO> likes = likeService.obtenerLikesDePublicacion(publicacion.getId());
                PublicacionDTO dto = publicacionMapper.toDTO(publicacion);
                dto.setLikes(likes);
                publicaciones.add(dto);
            }
        }

        return publicaciones != null ? publicaciones : new ArrayList<>();
    }
    
}
