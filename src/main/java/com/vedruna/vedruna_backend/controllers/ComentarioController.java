package com.vedruna.vedruna_backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.ComentarioDTO;
import com.vedruna.vedruna_backend.dto.ComentarioRequestDTO;
import com.vedruna.vedruna_backend.services.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

     // Obtener todos los comentarios de una publicación
     @GetMapping("/publicacion/{publicacionId}")
     public List<ComentarioDTO> obtenerComentariosPorPublicacion(@PathVariable Long publicacionId) {
         return comentarioService.obtenerComentariosPorPublicacion(publicacionId);
     }
 
     // Obtener comentarios de una publicación ordenados por fecha de creación
     @GetMapping("/publicacion/{publicacionId}/ordenados")
     public List<ComentarioDTO> obtenerComentariosPorPublicacionOrdenados(@PathVariable Long publicacionId) {
         return comentarioService.obtenerComentariosPorPublicacionOrdenados(publicacionId);
     }
 
     // Obtener respuestas a un comentario
     @GetMapping("/respuesta/{comentarioPadreId}")
     public List<ComentarioDTO> obtenerRespuestas(@PathVariable Long comentarioPadreId) {
         return comentarioService.obtenerRespuestas(comentarioPadreId);
     }
 
     // Obtener un comentario por su ID
     @GetMapping("/{id}")
     public Optional<ComentarioDTO> obtenerComentarioPorId(@PathVariable Long id) {
         return comentarioService.obtenerPorId(id);
     }
 
     // Crear un nuevo comentario
     @PostMapping
     public ComentarioDTO crearComentario(@RequestBody ComentarioRequestDTO comentarioRequestDTO) {
         return comentarioService.guardarComentario(comentarioRequestDTO);
     }
 
     // Eliminar un comentario por su ID
     @DeleteMapping("/{id}")
     public void eliminarComentario(@PathVariable Long id) {
         comentarioService.eliminarComentario(id);
     }
    
}
