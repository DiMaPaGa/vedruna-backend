package com.vedruna.vedruna_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.exceptions.SeguidorNotFoundException;
import com.vedruna.vedruna_backend.mappers.SeguidorMapper;
import com.vedruna.vedruna_backend.persistance.models.Seguidor;
import com.vedruna.vedruna_backend.persistance.models.SeguidorId;
import com.vedruna.vedruna_backend.persistance.repositories.SeguidorRepository;

@Service
public class SeguidorServiceImpl implements SeguidorService {

    @Autowired
    private SeguidorRepository seguidorRepository;

    @Autowired
    private SeguidorMapper seguidorMapper;

    @Transactional
    @Override
    public SeguidorDTO seguirUsuario(String seguidorId, String seguidoId) {
        Seguidor seguidor = new Seguidor();
        seguidor.setId(new SeguidorId(seguidorId, seguidoId));
        seguidor = seguidorRepository.save(seguidor);
        return seguidorMapper.toDTO(seguidor);
    }

    @Transactional
    @Override
    public void dejarDeSeguir(String seguidorId, String seguidoId) throws SeguidorNotFoundException {
        // Create the composite key for the seguidor
        SeguidorId id = new SeguidorId(seguidorId, seguidoId);

        // Search for the seguidor in the database
        Optional<Seguidor> seguidor = seguidorRepository.findById(id);

        // If the seguidor doesn't exist, throw an exception
        if (seguidor.isEmpty()) {
            throw new SeguidorNotFoundException("El seguidor no existe para estos usuarios");
        }

        // Delete the seguidor from the database
        seguidorRepository.delete(seguidor.get());
    }

    @Transactional(readOnly = true)
    @Override
    public List<SeguidorDTO> obtenerSeguidoresPorUsuario(String seguidoId) {
        List<Seguidor> seguidores = seguidorRepository.findByIdSeguidoId(seguidoId);
        return seguidores.stream()
                .map(seguidorMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<SeguidorDTO> obtenerUsuariosSeguidosPorUsuario(String seguidorId) {
        List<Seguidor> seguidos = seguidorRepository.findByIdSeguidorId(seguidorId);
        return seguidos.stream()
                .map(seguidorMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public long contarSeguidores(String seguidoId) {
        return seguidorRepository.countByIdSeguidoId(seguidoId);
    }

    @Transactional(readOnly = true)
    @Override
    public long contarSeguidosPorUsuario(String seguidorId) {
        return seguidorRepository.countByIdSeguidorId(seguidorId);
    }

    // Método para verificar si un usuario sigue a otro
    @Transactional(readOnly = true)
    public boolean esSeguidor(String seguidorId, String seguidoId) {
        return seguidorRepository.findByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId).isPresent();
    }
    
}
