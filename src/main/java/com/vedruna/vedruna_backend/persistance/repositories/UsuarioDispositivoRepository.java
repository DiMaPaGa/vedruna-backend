package com.vedruna.vedruna_backend.persistance.repositories;

import com.vedruna.vedruna_backend.persistance.models.UsuarioDispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link UsuarioDispositivo}.
 * Proporciona métodos para gestionar la relación entre usuarios y dispositivos,
 * así como consultas específicas para obtener tokens de notificación push.
 */
@Repository
public interface UsuarioDispositivoRepository extends JpaRepository<UsuarioDispositivo, Long> {

    /**
     * Verifica si existe una asociación entre un usuario y un dispositivo.
     *
     * @param usuario    Entidad Usuario.
     * @param dispositivo Entidad Dispositivo.
     * @return true si la asociación existe, false en caso contrario.
     */
    boolean existsByUsuarioIdAndDispositivoId(Usuario usuario, Dispositivo dispositivo);

    /**
     * Busca una asociación entre usuario y dispositivo por sus IDs.
     *
     * @param usuarioId    ID del usuario.
     * @param dispositivoId ID del dispositivo.
     * @return Optional que contiene la entidad UsuarioDispositivo si existe.
     */
    Optional<UsuarioDispositivo> findByUsuarioIdAndDispositivoId(Long usuarioId, Long dispositivoId);

     /**
     * Obtiene la lista de tokens Expo Push IDs asociados a un usuario dado su ID (clave primaria).
     *
     * @param usuarioId ID del usuario.
     * @return Lista de Expo Push IDs asociados a los dispositivos del usuario.
     */
    @Query("SELECT d.expoPushId FROM UsuarioDispositivo ud JOIN ud.dispositivo d WHERE ud.usuario.id = :usuarioId")
    List<String> findExpoPushTokensByUsuarioId(@Param("usuarioId") Long usuarioId);

    /**
     * Obtiene la lista de tokens Expo Push IDs asociados a un usuario dado su userId (campo String único).
     *
     * @param userId User ID único del usuario.
     * @return Lista de Expo Push IDs asociados a los dispositivos del usuario.
     */
    @Query("SELECT d.expoPushId FROM UsuarioDispositivo ud " +
       "JOIN ud.usuario u " +
       "JOIN ud.dispositivo d " +
       "WHERE u.userId = :userId")
    List<String> findExpoPushIdsByUserId(@Param("userId") String userId);
}
