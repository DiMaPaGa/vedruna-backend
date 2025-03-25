package com.vedruna.vedruna_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.vedruna_backend.dto.LikeDTO;
import com.vedruna.vedruna_backend.dto.LikeRequestDTO;
import com.vedruna.vedruna_backend.services.LikeService;



@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeDTO> darLike(@RequestBody LikeRequestDTO requestDTO) {
        return ResponseEntity.ok(likeService.darLike(requestDTO));
    }

    @DeleteMapping("/{userId}/{publicacionId}")
    public ResponseEntity<Void> quitarLike(@PathVariable String userId, @PathVariable Long publicacionId) {
        likeService.quitarLike(userId, publicacionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{publicacionId}")
    public ResponseEntity<List<LikeDTO>> obtenerLikes(@PathVariable Long publicacionId) {
        return ResponseEntity.ok(likeService.obtenerLikesDePublicacion(publicacionId));
    }
}
