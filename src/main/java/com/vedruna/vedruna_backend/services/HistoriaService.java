package com.vedruna.vedruna_backend.services;


import java.util.List;


import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.dto.HistoriaRequestDTO;




public interface HistoriaService {
    HistoriaDTO createHistoria(HistoriaRequestDTO requestDTO);
    
    List<HistoriaDTO> getAllHistorias(); 

    void deleteHistoria(Long historiaId, String userGoogleId); 

    HistoriaDTO getHistoriaById(Long historiaId);
}
