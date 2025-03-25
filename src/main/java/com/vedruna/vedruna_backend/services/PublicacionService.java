package com.vedruna.vedruna_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vedruna.vedruna_backend.dto.PublicacionDTO;
import com.vedruna.vedruna_backend.exceptions.ResourceNotFoundException;

@Service
public interface PublicacionService {
    PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    PublicacionDTO obtenerPublicacionPorId(Long id) throws ResourceNotFoundException;
    List<PublicacionDTO> obtenerPublicacionesPorUsuario(String userId);
    List<PublicacionDTO> obtenerPublicacionesConLikePorUsuario(String userId);
}
