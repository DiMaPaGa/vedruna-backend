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

@Service
public class LikeServiceImpl implements LikeService {
    
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private LikeMapper likeMapper;

    @Transactional
    @Override
    public LikeDTO darLike(LikeRequestDTO requestDTO) {
        // Verifica si el like ya existe
        Optional<Like> existingLike = likeRepository.findByLikeIdUserIdAndPublicacionId(requestDTO.getUserId(), requestDTO.getPublicacionId());
        if (existingLike.isPresent()) {
            throw new LikeAlreadyExistsException("El usuario ya ha dado like a esta publicación.");
        }

        // Obtiene la publicación a la que se le va a dar like
        Publicacion publicacion = publicacionRepository.findById(requestDTO.getPublicacionId())
                .orElseThrow(() -> new LikeNotFoundException("Publicación no encontrada."));

        // Crea un nuevo like
        Like nuevoLike = likeMapper.toEntity(requestDTO, publicacion);
        likeRepository.save(nuevoLike);
        return likeMapper.toDTO(nuevoLike);
    }

    @Transactional
    @Override
    public void quitarLike(String userId, Long publicacionId) {
        // Crear el LikeId con ambos parámetros
        LikeId likeId = new LikeId(userId, publicacionId);
        
        // Verifica si el like existe
        if (!likeRepository.existsById(likeId)) {
            throw new LikeNotFoundException("El like no existe.");
        }
        
        // Elimina el like
        likeRepository.deleteById(likeId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LikeDTO> obtenerLikesDePublicacion(Long publicacionId) {
        // Obtiene los likes asociados a una publicación
        return likeRepository.findByPublicacionId(publicacionId).stream()
                .map(likeMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean usuarioHaDadoLike(String userId, Long publicacionId) {
        // Verifica si un usuario ha dado like a una publicación
        return likeRepository.findByLikeIdUserIdAndPublicacionId(userId, publicacionId).isPresent();
    }

    @Transactional(readOnly = true)
    @Override
    public long contarLikesDePublicacion(Long publicacionId) {
        // Cuenta los likes de una publicación
        return likeRepository.countByPublicacionId(publicacionId);
    }
}