package com.vedruna.vedruna_backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.exceptions.SeguidorNotFoundException;
import com.vedruna.vedruna_backend.exceptions.SeguimientoExistenteException;
import com.vedruna.vedruna_backend.mappers.SeguidorMapper;
import com.vedruna.vedruna_backend.persistance.models.Estado;
import com.vedruna.vedruna_backend.persistance.models.Seguidor;
import com.vedruna.vedruna_backend.persistance.models.SeguidorId;
import com.vedruna.vedruna_backend.persistance.repositories.SeguidorRepository;

@Service
public class SeguidorServiceImpl implements SeguidorService {

    @Autowired
    private SeguidorRepository seguidorRepository;

    @Autowired
    private SeguidorMapper seguidorMapper;

    public Page<SeguidorDTO> obtenerSeguidores(String seguidoId, Estado estado, Pageable pageable) {
        return seguidorRepository
                .findByIdSeguidoIdAndEstado(seguidoId, estado, pageable)
                .map(seguidorMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SeguidorDTO> obtenerSeguidos(String seguidorId, Estado estado, Pageable pageable) {
        return seguidorRepository
                .findByIdSeguidorIdAndEstado(seguidorId, estado, pageable)
                .map(seguidorMapper::toDTO);
    }


    // Método para verificar si un usuario sigue a otro
    @Transactional(readOnly = true)
    @Override
    public boolean esSeguidor(String seguidorId, String seguidoId) {
        return seguidorRepository.findByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public long contarSeguidores(String seguidoId, Estado estado) {
        return seguidorRepository.countByIdSeguidoIdAndEstado(seguidoId, estado);
    }

    @Override
    @Transactional(readOnly = true)
    public long contarSeguidos(String seguidorId, Estado estado) {
        return seguidorRepository.countByIdSeguidorIdAndEstado(seguidorId, estado);
    }

    @Override
    @Transactional
    public void seguirUsuario(String seguidorId, String seguidoId) {
        if (seguidorId.equals(seguidoId)) {
            throw new IllegalArgumentException("No puedes seguirte a ti mismo");
        }

        // Verificar si ya existe la relación
        Optional<Seguidor> existente = seguidorRepository.findByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId);
        if (existente.isPresent()) {
            throw new SeguimientoExistenteException("Ya existe una relación de seguimiento entre estos usuarios.");
        }

        // Verificar si el seguido ya sigue al seguidor con estado ACEPTADO
        Optional<Seguidor> inversa = seguidorRepository.findByIdSeguidorIdAndIdSeguidoId(seguidoId, seguidorId);

        Estado estado = Estado.PENDIENTE;

        if (inversa.isPresent() && inversa.get().getEstado() == Estado.ACEPTADO) {
            estado = Estado.ACEPTADO; // Relación inversa ya aceptada → nos ahorramos la espera
        }

        Seguidor nuevo = new Seguidor(new SeguidorId(seguidorId, seguidoId), estado);
        seguidorRepository.save(nuevo);
    }

    @Override
    @Transactional
    public void dejarDeSeguir(String seguidorId, String seguidoId) {
        if (!seguidorRepository.existsByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId)) {
            throw new SeguidorNotFoundException("No se encontró el seguimiento para eliminar.");
        }
        seguidorRepository.deleteByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId);
    }

    @Override
    @Transactional
    public void aceptarSolicitud(String seguidorId, String seguidoId) {
        Optional<Seguidor> optional = seguidorRepository.findByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId);
        if (optional.isPresent()) {
            Seguidor seguidor = optional.get();
            seguidor.setEstado(Estado.ACEPTADO);
            seguidorRepository.save(seguidor);
        } else {
            throw new SeguidorNotFoundException("No existe ninguna solicitud de seguimiento pendiente.");
        }
    }
}