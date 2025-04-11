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
    public Like obtenerOCrearLike(String userId, Long publicacionId) {
        // Verifica si el like ya existe
        Optional<Like> existingLike = likeRepository.findByLikeIdUserIdAndPublicacionId(userId, publicacionId);
        if (existingLike.isPresent()) {
            return existingLike.get(); // Si el like ya existe, lo retornamos
        }
        // Si no existe, creamos un nuevo like
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new LikeNotFoundException("Publicación no encontrada."));
        Like nuevoLike = new Like();
        nuevoLike.setLikeId(new LikeId(userId, publicacionId));
        nuevoLike.setPublicacion(publicacion);
        return likeRepository.save(nuevoLike); // Lo guardamos y lo retornamos
    }

    @Transactional
    @Override
    public LikeDTO darLike(LikeRequestDTO requestDTO) {
        // Verifica si ya existe el like antes de agregarlo
        Optional<Like> existingLike = likeRepository.findByLikeIdUserIdAndPublicacionId(requestDTO.getUserId(), requestDTO.getPublicacionId());
        if (existingLike.isPresent()) {
            throw new LikeAlreadyExistsException("El usuario ya ha dado like a esta publicación.");
        }
        
        // Si no existe, se crea y guarda el like
        Publicacion publicacion = publicacionRepository.findById(requestDTO.getPublicacionId())
                .orElseThrow(() -> new LikeNotFoundException("Publicación no encontrada."));
        Like nuevoLike = new Like();
        nuevoLike.setLikeId(new LikeId(requestDTO.getUserId(), requestDTO.getPublicacionId()));
        nuevoLike.setPublicacion(publicacion);
        likeRepository.save(nuevoLike);
        
        return likeMapper.toDTO(nuevoLike);
    }

    @Transactional
    @Override
    public void quitarLike(String userId, Long publicacionId) {
        // Usamos el LikeId para eliminar el like de la base de datos
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
