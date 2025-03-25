package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.LikeRequestDTO;

@Service
public interface LikeService {
    LikeDTO darLike(LikeRequestDTO requestDTO);
    void quitarLike(String userId, Long publicacionId);
    List<LikeDTO> obtenerLikesDePublicacion(Long publicacionId);
    boolean usuarioHaDadoLike(String userId, Long publicacionId);
    long contarLikesDePublicacion(Long publicacionId);
}
