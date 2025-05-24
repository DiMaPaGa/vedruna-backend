package com.vedruna.vedruna_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.LikeRequestDTO;
import com.vedruna.vedruna_backend.exceptions.LikeAlreadyExistsException;
import com.vedruna.vedruna_backend.exceptions.LikeNotFoundException;
import com.vedruna.vedruna_backend.mappers.LikeMapper;
import com.vedruna.vedruna_backend.persistance.models.Like;
import com.vedruna.vedruna_backend.persistance.models.LikeId;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;
import com.vedruna.vedruna_backend.persistance.repositories.LikeRepository;
import com.vedruna.vedruna_backend.persistance.repositories.PublicacionRepository;

/**
 * Implementación del servicio para la gestión de likes.
 */
@Service
public class LikeServiceImpl implements LikeService {
    
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private LikeMapper likeMapper;

    /**
     * Obtiene o crea un like asociado a una publicación.
     *
     * @param userId el ID del usuario
     * @param publicacionId el ID de la publicación
     * @return el like obtenido o creado
     * @throws LikeNotFoundException si la publicación no se encuentra
     */
    @Transactional
    public Like obtenerOCrearLike(String userId, Long publicacionId) {
        // Verifica si el like ya existe
        Optional<Like> existingLike = likeRepository.findByLikeIdUserIdAndPublicacionId(userId, publicacionId);
        if (existingLike.isPresent()) {
            // Si el like ya existe, lo retorna
            return existingLike.get(); 
        }
        // Si no existe, se crea y guarda el like
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new LikeNotFoundException("Publicación no encontrada."));
        Like nuevoLike = new Like();
        nuevoLike.setLikeId(new LikeId(userId, publicacionId));
        nuevoLike.setPublicacion(publicacion);
        return likeRepository.save(nuevoLike); 
    }

    
    /**
     * Registra un nuevo like a una publicación.
     *
     * @param requestDTO el objeto con los datos del like a crear
     * @return el objeto LikeDTO creado
     * @throws LikeAlreadyExistsException si el like ya existe
     * @throws LikeNotFoundException si la publicación no se encuentra
     */
    @Transactional
    @Override
    public LikeDTO darLike(LikeRequestDTO requestDTO) {
        
        Optional<Like> existingLike = likeRepository.findByLikeIdUserIdAndPublicacionId(requestDTO.getUserId(), requestDTO.getPublicacionId());
        if (existingLike.isPresent()) {
            throw new LikeAlreadyExistsException("El usuario ya ha dado like a esta publicación.");
        }
        
        
        Publicacion publicacion = publicacionRepository.findById(requestDTO.getPublicacionId())
                .orElseThrow(() -> new LikeNotFoundException("Publicación no encontrada."));
        Like nuevoLike = new Like();
        nuevoLike.setLikeId(new LikeId(requestDTO.getUserId(), requestDTO.getPublicacionId()));
        nuevoLike.setPublicacion(publicacion);
        likeRepository.save(nuevoLike);
        
        return likeMapper.toDTO(nuevoLike);
    }

    /**
     * Elimina un like existente para un usuario y publicación dados.
     * 
     * @param userId El identificador único del usuario que da el like.
     * @param publicacionId El identificador único de la publicación que recibe el like.
     * @throws LikeNotFoundException si el like no existe.
     */
    @Transactional
    @Override
    public void quitarLike(String userId, Long publicacionId) {
        
        LikeId likeId = new LikeId(userId, publicacionId);

        if (!likeRepository.existsById(likeId)) {
            throw new LikeNotFoundException("El like no existe.");
        }
        
        likeRepository.deleteById(likeId);
    }

    
    /**
     * Obtiene todos los likes asociados a una publicación.
     *
     * @param publicacionId El identificador único de la publicación.
     * @return Una lista de objetos LikeDTO que representan los likes de la publicación.
     */
    @Transactional(readOnly = true)
    @Override
    public List<LikeDTO> obtenerLikesDePublicacion(Long publicacionId) {
        // Obtiene los likes asociados a una publicación
        return likeRepository.findByPublicacionId(publicacionId).stream()
                .map(likeMapper::toDTO)
                .toList();
    }

    /**
     * Verifica si un usuario ha dado like a una publicación.
     *
     * @param userId       El identificador único del usuario.
     * @param publicacionId El identificador único de la publicación.
     * @return {@code true} si el usuario ha dado like, {@code false} en caso contrario.
     */
    @Transactional(readOnly = true)
    @Override
    public boolean usuarioHaDadoLike(String userId, Long publicacionId) {
        // Verifica si un usuario ha dado like a una publicación
        return likeRepository.findByLikeIdUserIdAndPublicacionId(userId, publicacionId).isPresent();
    }

    /**
     * Cuenta la cantidad total de likes que tiene una publicación.
     *
     * @param publicacionId El identificador único de la publicación.
     * @return La cantidad total de likes asociados a la publicación.
     */
    @Transactional(readOnly = true)
    @Override
    public long contarLikesDePublicacion(Long publicacionId) {
        // Cuenta los likes de una publicación
        return likeRepository.countByPublicacionId(publicacionId);
    }
}
