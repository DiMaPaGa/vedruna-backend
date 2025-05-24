package com.vedruna.vedruna_backend.services;

import java.util.List;
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
import com.vedruna.vedruna_backend.persistance.repositories.UsuarioDispositivoRepository;

/**
 * Implementación de la interfaz SeguidorService.
 */
@Service
public class SeguidorServiceImpl implements SeguidorService {

    @Autowired
    private SeguidorRepository seguidorRepository;

    @Autowired
    private SeguidorMapper seguidorMapper;

    @Autowired
    private UsuarioDispositivoRepository usuarioDispositivoRepository;

    @Autowired
    private NotificacionService notificacionService; 

/**
 * Obtiene paginados los seguidores de un usuario con un estado específico.
 *
 * @param seguidoId ID del usuario seguido.
 * @param estado Estado del seguimiento (ej. ACEPTADO, PENDIENTE).
 * @param pageable Información de paginación.
 * @return Página con los DTOs de seguidores.
 */

    public Page<SeguidorDTO> obtenerSeguidores(String seguidoId, Estado estado, Pageable pageable) {
        return seguidorRepository
                .findByIdSeguidoIdAndEstado(seguidoId, estado, pageable)
                .map(seguidorMapper::toDTO);
    }

    /**
     * Obtiene paginados los usuarios seguidos por un usuario con un estado específico.
     *
     * @param seguidorId ID del usuario seguidor.
     * @param estado Estado del seguimiento.
     * @param pageable Información de paginación.
     * @return Página con los DTOs de usuarios seguidos.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SeguidorDTO> obtenerSeguidos(String seguidorId, Estado estado, Pageable pageable) {
        return seguidorRepository
                .findByIdSeguidorIdAndEstado(seguidorId, estado, pageable)
                .map(seguidorMapper::toDTO);
    }


    
    /**
     * Verifica si un usuario sigue a otro.
     *
     * @param seguidorId ID del usuario seguidor.
     * @param seguidoId ID del usuario seguido.
     * @return true si el seguidor sigue al seguido, false en caso contrario.
     */
    @Transactional(readOnly = true)
    @Override
    public boolean esSeguidor(String seguidorId, String seguidoId) {
        return seguidorRepository.findByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId).isPresent();
    }

    /**
     * Cuenta el número de seguidores de un usuario con un estado específico.
     *
     * @param seguidoId ID del usuario seguido.
     * @param estado Estado del seguimiento.
     * @return Número de seguidores.
     */
    @Override
    @Transactional(readOnly = true)
    public long contarSeguidores(String seguidoId, Estado estado) {
        return seguidorRepository.countByIdSeguidoIdAndEstado(seguidoId, estado);
    }

    /**
     * Cuenta el número de usuarios seguidos por un usuario con un estado específico.
     *
     * @param seguidorId ID del usuario seguidor.
     * @param estado Estado del seguimiento.
     * @return Número de usuarios seguidos.
     */
    @Override
    @Transactional(readOnly = true)
    public long contarSeguidos(String seguidorId, Estado estado) {
        return seguidorRepository.countByIdSeguidorIdAndEstado(seguidorId, estado);
    }

    /**
     * Establece una relación de seguimiento entre dos usuarios.
     *
     * Verifica si ya existe la relación y si el seguido ya sigue al seguidor con estado ACEPTADO.
     * Si no existe la relación, crea una nueva con estado PENDIENTE.
     * Si existe la relación inversa con estado ACEPTADO, nos ahorramos la espera y creamos la relación
     * directa con estado ACEPTADO.
     *
     * Envía una notificación push al seguido si se crea una solicitud pendiente.
     *
     * @param seguidorId ID del usuario seguidor.
     * @param seguidoId ID del usuario seguido.
     * @throws IllegalArgumentException si el seguidor intenta seguirse a sí mismo.
     * @throws SeguimientoExistenteException si ya existe una relación de seguimiento entre estos usuarios.
     */
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

         // Enviar notificación SOLO si es una solicitud pendiente
         if (estado == Estado.PENDIENTE) {
            List<String> tokens = usuarioDispositivoRepository.findExpoPushIdsByUserId(seguidoId);
            if (!tokens.isEmpty()) {
                notificacionService.enviarNotificacion(
                    tokens,
                    "Nueva solicitud de amistad",
                    "¡Tienes una solicitud de amistad nueva!"
                );
            }
        }
    }
    

    /**
     * Elimina el seguimiento de un usuario.
     * 
     * @param seguidorId ID del usuario que deja de seguir.
     * @param seguidoId ID del usuario que deja de ser seguido.
     * @throws SeguidorNotFoundException si el seguimiento no existe.
     */
    @Override
    @Transactional
    public void dejarDeSeguir(String seguidorId, String seguidoId) {
        if (!seguidorRepository.existsByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId)) {
            throw new SeguidorNotFoundException("No se encontró el seguimiento para eliminar.");
        }
        seguidorRepository.deleteByIdSeguidorIdAndIdSeguidoId(seguidorId, seguidoId);
    }

    /**
     * Acepta una solicitud de seguimiento pendiente.
     *
     * Verifica si existe la solicitud de seguimiento pendiente y la actualiza
     * con estado ACEPTADO.
     *
     * @param seguidorId ID del usuario que solicitó seguir.
     * @param seguidoId ID del usuario que acepta la solicitud.
     * @throws SeguidorNotFoundException si no existe la solicitud de seguimiento pendiente.
     */
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