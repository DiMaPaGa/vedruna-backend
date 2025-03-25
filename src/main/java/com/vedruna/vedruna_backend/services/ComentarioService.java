package com.vedruna.vedruna_backend.services;

import java.util.List;
import java.util.Optional;

import com.vedruna.vedruna_backend.dto.ComentarioDTO;
import com.vedruna.vedruna_backend.dto.ComentarioRequestDTO;

public interface ComentarioService {
    List<ComentarioDTO> obtenerComentariosPorPublicacion(Long publicacionId);
    List<ComentarioDTO> obtenerComentariosPorPublicacionOrdenados(Long publicacionId);
    List<ComentarioDTO> obtenerRespuestas(Long comentarioPadreId);
    Optional<ComentarioDTO> obtenerPorId(Long id);
    ComentarioDTO guardarComentario(ComentarioRequestDTO comentarioDTO);
    void eliminarComentario(Long id);
    
}
