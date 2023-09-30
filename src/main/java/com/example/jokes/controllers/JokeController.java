package com.example.jokes.controllers;

import com.example.jokes.domain.JokeDto;
import com.example.jokes.mapper.JokeMapper;
import com.example.jokes.service.JokeDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/jokes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JokeController {

    private final JokeMapper jokeMapper;
    private final JokeDbService jokeDbService;

    @Autowired
    public JokeController(JokeDbService jokeDbService, JokeMapper jokeMapper) {
        this.jokeDbService = jokeDbService;
        this.jokeMapper = jokeMapper;
    }


    @PostMapping
    public ResponseEntity<Void> createJoke(@RequestBody JokeDto jokeDto) {
        jokeDbService.addJoke(jokeMapper.mapToJoke(jokeDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<JokeDto>> getJokes() {
        List<JokeDto> jokes = jokeMapper.mapToJokeDtoList(jokeDbService.getAllJokes());
        return ResponseEntity.ok(jokes);
    }

    @GetMapping("/{jokeId}")
    public ResponseEntity<JokeDto> getJoke(@PathVariable long jokeId) {
        return jokeDbService.getJokeById(jokeId)
                .map(joke -> ResponseEntity.ok(jokeMapper.mapToJokeDto(joke)))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{jokeId}")
    public ResponseEntity<Void> deleteJoke(@PathVariable long jokeId) {
        jokeDbService.deleteJokeById(jokeId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{jokeId}")
    public ResponseEntity<JokeDto> updateJoke(@PathVariable long jokeId, @RequestBody JokeDto jokeDto) {
        return ResponseEntity.ok(jokeDto);
    }
}