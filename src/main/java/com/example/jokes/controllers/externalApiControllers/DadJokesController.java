package com.example.jokes.controllers.externalApiControllers;

import com.example.jokes.service.externalApiDbService.DadJokeDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/dadJoke")
@RequiredArgsConstructor
public class DadJokesController {

    private final DadJokeDbService dadJokeDbService;
    @GetMapping("/getRanodomJoke")
    public ResponseEntity<?>  getRandomDadJoke (){
        return ResponseEntity.ok(dadJokeDbService.getRandomJoke());
    }

}
