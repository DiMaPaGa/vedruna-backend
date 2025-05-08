package com.vedruna.vedruna_backend.services;

import java.util.List;

import com.vedruna.vedruna_backend.dto.UsuarioDispositivoDTO;
import com.vedruna.vedruna_backend.persistance.models.Dispositivo;
import com.vedruna.vedruna_backend.persistance.models.Usuario;

public interface UsuarioDispositivoService {
    void guardarRelacionUsuarioDispositivo(Usuario usuario, Dispositivo dispositivo);
    UsuarioDispositivoDTO obtenerRelacionUsuarioDispositivo(Long usuarioId, Long dispositivoId);
    List<String> obtenerTokensPorUserId(String userId);
}