package com.vedruna.vedruna_backend.services;

import java.util.List;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.exceptions.SeguidorNotFoundException;

public interface SeguidorService {
    SeguidorDTO seguirUsuario(String seguidorId, String seguidoId);
    void dejarDeSeguir(String seguidorId, String seguidoId) throws SeguidorNotFoundException;
    List<SeguidorDTO> obtenerSeguidoresPorUsuario(String seguidoId);
    List<SeguidorDTO> obtenerUsuariosSeguidosPorUsuario(String seguidorId);
    long contarSeguidores(String seguidoId);
    long contarSeguidosPorUsuario(String seguidorId);
    boolean verificarSiUsuarioSigueA(String seguidorId, String seguidoId);
}
