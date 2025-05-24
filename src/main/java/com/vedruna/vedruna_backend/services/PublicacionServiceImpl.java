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

/**
 * Implementación de la interfaz PublicacionService.
 */
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


    /**
     * Crea una publicación con los datos del DTO recibido.
     * 
     * @param publicacionDTO el DTO con los datos para crear la publicación
     * @return el DTO de la publicación creada
     */
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


    /**
     * Obtiene una publicación por su ID.
     * 
     * @param id el ID de la publicación a obtener
     * @return el DTO de la publicación encontrada
     * @throws ResourceNotFoundException si la publicación no se encuentra
     */
    @Transactional(readOnly = true)
    @Override
    public PublicacionDTO obtenerPublicacionPorId(Long id) throws ResourceNotFoundException {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicación no encontrada"));
        return publicacionMapper.toDTO(publicacion);
    }

    
    /**
     * Obtiene todas las publicaciones creadas por un usuario especifico.
     * 
     * @param userId el ID del usuario cuyas publicaciones se desean obtener
     * @return una lista de DTO de las publicaciones encontradas
     */
    @Transactional(readOnly = true)
    @Override
    public List<PublicacionDTO> obtenerPublicacionesPorUsuario(String userId) {
        List<Publicacion> publicaciones = publicacionRepository.findByAutor_UserId(userId);
        return publicaciones.stream()
                .map(publicacionMapper::toDTO)
                .toList();
    }

    
    /**
     * Obtiene todas las publicaciones que un usuario ha marcado con like.
     * 
     * @param userId el ID del usuario cuyas publicaciones se desean obtener
     * @return una lista de DTO de las publicaciones encontradas
     */
    @Transactional(readOnly = true)
    @Override
    public List<PublicacionDTO> obtenerPublicacionesConLikePorUsuario(String userId) {
        List<Publicacion> publicaciones = publicacionRepository.findByLikes_LikeId_UserId(userId);
        return publicaciones.stream()
                .map(publicacionMapper::toDTO)
                .toList();
    }

    
    /**
     * Obtiene todas las publicaciones accesibles para un usuario, incluyendo públicas y privadas
     * si el usuario es seguidor de la persona autora.
     * 
     * @param userId el ID del usuario cuyas publicaciones se desean obtener
     * @return una lista de DTO de las publicaciones encontradas
     */
    @Transactional(readOnly = true)
    @Override
    public List<PublicacionDTO> obtenerTodasLasPublicaciones(String userId) {
        List<PublicacionDTO> publicaciones = new ArrayList<>();
       
        List<Publicacion> publicacionesPublicas = publicacionRepository.findByPrivacidad(Privacidad.PUBLICA);
        publicacionesPublicas.forEach(publicacion -> {
           
            List<LikeDTO> likes = likeService.obtenerLikesDePublicacion(publicacion.getId());
            PublicacionDTO dto = publicacionMapper.toDTO(publicacion);
            dto.setLikes(likes);
            publicaciones.add(dto);
        });

        List<Publicacion> publicacionesPrivadas = publicacionRepository.findByPrivacidad(Privacidad.PRIVADA);
        for (Publicacion publicacion : publicacionesPrivadas) {
            
            if (seguidorService.esSeguidor(userId, publicacion.getAutor().getUserId()) || publicacion.getAutor().getUserId().equals(userId)) {
                List<LikeDTO> likes = likeService.obtenerLikesDePublicacion(publicacion.getId());
                PublicacionDTO dto = publicacionMapper.toDTO(publicacion);
                dto.setLikes(likes);
                publicaciones.add(dto);
            }
        }

        publicaciones.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));

        return publicaciones != null ? publicaciones : new ArrayList<>();
    }
    
}
