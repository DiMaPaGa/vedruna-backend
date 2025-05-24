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

/**
 * Implementación del servicio para la gestión de comentarios.
 * Proporciona operaciones para obtener, guardar y eliminar comentarios
 * relacionados con publicaciones.
 */
@Service
public class ComentarioServiceImpl implements ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Autowired
    private PublicacionRepository publicacionRepository; // <-- Faltaba inyectar este repositorio

    @Autowired
    private ComentarioMapper comentarioMapper;

    /**
     * Obtiene todos los comentarios asociados a una publicación específica.
     *
     * @param publicacionId el ID de la publicación cuyos comentarios se desean obtener.
     * @return una lista de objetos ComentarioDTO correspondientes a los comentarios encontrados.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ComentarioDTO> obtenerComentariosPorPublicacion(Long publicacionId) {
        return comentarioRepository.findAllByPublicacionId(publicacionId)
                .stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }


    /**
     * Obtiene todos los comentarios asociados a una publicación específica, ordenados
     * de forma descendente por fecha de creación.
     *
     * @param publicacionId el ID de la publicación cuyos comentarios se desean obtener.
     * @return una lista de objetos ComentarioDTO ordenados por fecha de creación descendente.
     */
    @Override
    @Transactional(readOnly = true) // Solo lectura, optimiza la consulta
    public List<ComentarioDTO> obtenerComentariosPorPublicacionOrdenados(Long publicacionId) {
        return comentarioRepository.findAllByPublicacionIdOrderByCreatedAtDesc(publicacionId)
                .stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }

    /**
     * Obtiene todas las respuestas a un comentario padre dado.
     *
     * @param comentarioPadreId el ID del comentario padre para el cual se buscan respuestas.
     * @return una lista de objetos ComentarioDTO que son respuestas al comentario padre.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ComentarioDTO> obtenerRespuestas(Long comentarioPadreId) {
        List<Comentario> comentarios = comentarioRepository.findAllByComentarioPadreId(comentarioPadreId);
    
    return comentarios.stream()
            .map(comentarioMapper::toDTO)
            .toList();
    }

    /**
     * Obtiene un comentario por su ID.
     *
     * @param id el ID del comentario a obtener.
     * @return un Optional que contiene el ComentarioDTO si se encuentra, o vacío si no.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComentarioDTO> obtenerPorId(Long id) {
        return comentarioRepository.findById(id).map(comentarioMapper::toDTO);
    }

    /**
     * Guarda un nuevo comentario en la base de datos.
     * Si el comentario es respuesta a otro comentario, se establece la relación padre.
     *
     * @param comentarioDTO el objeto que contiene los datos del comentario a guardar.
     * @return el ComentarioDTO con los datos guardados y actualizados.
     * @throws PublicacionNotFoundException si la publicación asociada no existe.
     * @throws ComentarioNotFoundException si el comentario padre no existe (cuando aplica).
     */
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

    /**
     * Elimina un comentario por su ID.
     *
     * @param id el ID del comentario a eliminar.
     * @throws ComentarioNotFoundException si el comentario con el ID proporcionado no existe.
     */
    @Override
    @Transactional
    public void eliminarComentario(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ComentarioNotFoundException("Comentario con ID " + id + " no encontrado");
        }
        comentarioRepository.deleteById(id);
    }

    
}