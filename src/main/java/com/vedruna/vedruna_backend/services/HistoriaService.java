package com.vedruna.vedruna_backend.services;

import java.util.List;
import com.vedruna.vedruna_backend.dto.HistoriaDTO;
import com.vedruna.vedruna_backend.dto.HistoriaRequestDTO;

/*
 * Interfaz que representa el servicio para gestionar las historias.
 */
public interface HistoriaService {

    /*
     * Crea una nueva historia con los datos proporcionados.
     * 
     * @param requestDTO los datos para crear la historia.
     * @return el DTO de la historia creada.
     */
    HistoriaDTO createHistoria(HistoriaRequestDTO requestDTO);

    /*
     * Obtiene todas las historias existentes.
     * 
     * @return una lista de DTOs de las historias existentes.
     */
    List<HistoriaDTO> getAllHistorias(); 

    /*
     * Elimina una historia con el ID proporcionado y el ID de usuario Google.
     * 
     * @param historiaId el ID de la historia a eliminar.
     * @param userGoogleId el ID de usuario Google que realiza la eliminación.
     */
    void deleteHistoria(Long historiaId, String userGoogleId); 

    /*
     * Obtiene una historia por su ID.
     * 
     * @param historiaId el ID de la historia a obtener.
     * @return el DTO de la historia obtenida.
     */
    HistoriaDTO getHistoriaById(Long historiaId);
}
