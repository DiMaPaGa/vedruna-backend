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

@Repository
public interface UsuarioDispositivoRepository extends JpaRepository<UsuarioDispositivo, Long> {
    boolean existsByUsuarioIdAndDispositivoId(Usuario usuario, Dispositivo dispositivo);
    Optional<UsuarioDispositivo> findByUsuarioIdAndDispositivoId(Long usuarioId, Long dispositivoId);

    @Query("SELECT d.expoPushId FROM UsuarioDispositivo ud JOIN ud.dispositivo d WHERE ud.usuario.id = :usuarioId")
    List<String> findExpoPushTokensByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT d.expoPushId FROM UsuarioDispositivo ud " +
       "JOIN ud.usuario u " +
       "JOIN ud.dispositivo d " +
       "WHERE u.userId = :userId")
    List<String> findExpoPushIdsByUserId(@Param("userId") String userId);
}
