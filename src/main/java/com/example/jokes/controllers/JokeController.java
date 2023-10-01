package com.example.jokes.controllers;

import com.example.jokes.domain.Joke;
import com.example.jokes.domain.JokeDto;
import com.example.jokes.mapper.JokeMapper;
import com.example.jokes.service.JokeDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(jokeMapper.mapToJokeDto(jokeDbService.getJokeById(jokeId)));
    }


    @DeleteMapping("/{jokeId}")
    public ResponseEntity<Void> deleteJoke(@PathVariable long jokeId) {
        jokeDbService.deleteJokeById(jokeId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{jokeId}")
    public ResponseEntity<JokeDto> updateJoke(@PathVariable long jokeId, @RequestBody JokeDto jokeDto) {
        Joke joke = jokeDbService.getJokeById(jokeId);

        return ResponseEntity.ok(jokeDto);
    }

    @GetMapping("/search/{author}")
    public ResponseEntity<List<JokeDto>> getJokesByAuthorLike(@PathVariable String author) {
        return ResponseEntity.ok(jokeMapper.mapToJokeDtoList(jokeDbService.getByAuthorNick(author)));
    }
}