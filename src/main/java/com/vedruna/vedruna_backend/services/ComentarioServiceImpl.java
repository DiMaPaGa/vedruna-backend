package com.vedruna.vedruna_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.ComentarioDTO;
import com.vedruna.vedruna_backend.dto.ComentarioRequestDTO;
import com.vedruna.vedruna_backend.exceptions.ComentarioNotFoundException;
import com.vedruna.vedruna_backend.exceptions.PublicacionNotFoundException;
import com.vedruna.vedruna_backend.mappers.ComentarioMapper;
import com.vedruna.vedruna_backend.persistance.models.Comentario;
import com.vedruna.vedruna_backend.persistance.models.Publicacion;
import com.vedruna.vedruna_backend.persistance.repositories.ComentarioRepository;
import com.vedruna.vedruna_backend.persistance.repositories.PublicacionRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Autowired
    private PublicacionRepository publicacionRepository; // <-- Faltaba inyectar este repositorio

    @Autowired
    private ComentarioMapper comentarioMapper;

    @Override
    @Transactional(readOnly = true) // Solo lectura, optimiza la consulta
    public List<ComentarioDTO> obtenerComentariosPorPublicacion(Long publicacionId) {
        return comentarioRepository.findAllByPublicacionId(publicacionId)
                .stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true) // Solo lectura, optimiza la consulta
    public List<ComentarioDTO> obtenerComentariosPorPublicacionOrdenados(Long publicacionId) {
        return comentarioRepository.findAllByPublicacionIdOrderByCreatedAtDesc(publicacionId)
                .stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioDTO> obtenerRespuestas(Long comentarioPadreId) {
        List<Comentario> comentarios = comentarioRepository.findAllByComentarioPadreId(comentarioPadreId);
    
    return comentarios.stream()
            .map(comentarioMapper::toDTO)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ComentarioDTO> obtenerPorId(Long id) {
        return comentarioRepository.findById(id).map(comentarioMapper::toDTO);
    }

    @Override
    @Transactional
    public ComentarioDTO guardarComentario(ComentarioRequestDTO comentarioDTO) {
        Comentario comentario = comentarioMapper.toEntity(comentarioDTO);

        // Buscar la publicación por ID
        Publicacion publicacion = publicacionRepository.findById(comentarioDTO.getPublicacionId())
                .orElseThrow(() -> new PublicacionNotFoundException("Publicación con ID " + comentarioDTO.getPublicacionId() + " no encontrada"));
        comentario.setPublicacion(publicacion);

        // Si es respuesta a otro comentario
        if (comentarioDTO.getComentarioPadreId() != null) {
            Comentario comentarioPadre = comentarioRepository.findById(comentarioDTO.getComentarioPadreId())
                    .orElseThrow(() -> new ComentarioNotFoundException("Comentario padre con ID " + comentarioDTO.getComentarioPadreId() + " no encontrado"));
            comentario.setComentarioPadre(comentarioPadre);
        }

        Comentario comentarioGuardado = comentarioRepository.save(comentario);
        return comentarioMapper.toDTO(comentarioGuardado);
    }

    @Override
    @Transactional
    public void eliminarComentario(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ComentarioNotFoundException("Comentario con ID " + id + " no encontrado");
        }
        comentarioRepository.deleteById(id);
    }

    
}