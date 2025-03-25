package com.vedruna.vedruna_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.SeguidorDTO;
import com.vedruna.vedruna_backend.exceptions.SeguidorNotFoundException;
import com.vedruna.vedruna_backend.services.SeguidorService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/seguidores")
public class SeguidorController {

    @Autowired
    private SeguidorService seguidorService;

     @PostMapping("/{seguidorId}/{seguidoId}")
    public ResponseEntity<SeguidorDTO> seguirUsuario(@PathVariable String seguidorId, @PathVariable String seguidoId) {
        SeguidorDTO seguidorDTO = seguidorService.seguirUsuario(seguidorId, seguidoId);
        return new ResponseEntity<>(seguidorDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{seguidorId}/{seguidoId}")
    public ResponseEntity<Void> dejarDeSeguir(@PathVariable String seguidorId, @PathVariable String seguidoId) throws SeguidorNotFoundException {
        seguidorService.dejarDeSeguir(seguidorId, seguidoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/seguidores/{seguidoId}")
    public ResponseEntity<List<SeguidorDTO>> obtenerSeguidores(@PathVariable String seguidoId) {
        List<SeguidorDTO> seguidores = seguidorService.obtenerSeguidoresPorUsuario(seguidoId);
        return new ResponseEntity<>(seguidores, HttpStatus.OK);
    }

    @GetMapping("/seguidos/{seguidorId}")
    public ResponseEntity<List<SeguidorDTO>> obtenerSeguidos(@PathVariable String seguidorId) {
        List<SeguidorDTO> seguidos = seguidorService.obtenerUsuariosSeguidosPorUsuario(seguidorId);
        return new ResponseEntity<>(seguidos, HttpStatus.OK);
    }

    @GetMapping("/count/seguidores/{seguidoId}")
    public ResponseEntity<Long> contarSeguidores(@PathVariable String seguidoId) {
        long count = seguidorService.contarSeguidores(seguidoId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/count/seguidos/{seguidorId}")
    public ResponseEntity<Long> contarSeguidos(@PathVariable String seguidorId) {
        long count = seguidorService.contarSeguidosPorUsuario(seguidorId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/verificar/{seguidorId}/{seguidoId}")
    public ResponseEntity<Boolean> verificarSiSigueA(@PathVariable String seguidorId, @PathVariable String seguidoId) {
        boolean sigue = seguidorService.verificarSiUsuarioSigueA(seguidorId, seguidoId);
        return new ResponseEntity<>(sigue, HttpStatus.OK);
    }
    
}
