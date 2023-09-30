package com.example.jokes.controllers;

import com.example.jokes.domain.JokeRatingDto;
import com.example.jokes.mapper.JokeRatingMapper;
import com.example.jokes.service.JokeRatingDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ratings")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JokeRatingController {
    private final JokeRatingMapper jokeRatingMapper;
    private final JokeRatingDbService jokeRatingDbService;

    @Autowired
    public JokeRatingController(JokeRatingDbService jokeRatingDbService, JokeRatingMapper jokeRatingMapper) {
        this.jokeRatingDbService = jokeRatingDbService;
        this.jokeRatingMapper = jokeRatingMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addJokeRating(@RequestBody JokeRatingDto jokeRatingDto) {
        jokeRatingDbService.addJokeRating(jokeRatingMapper.mapToJokeRating(jokeRatingDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<JokeRatingDto>> getAllJokeRatings() {
        List<JokeRatingDto> jokeRatings = jokeRatingMapper.mapToJokeRatingDtoList(jokeRatingDbService.getAllJokeRatings());
        return ResponseEntity.ok(jokeRatings);
    }

    @GetMapping("/{jokeRatingId}")
    public ResponseEntity<JokeRatingDto> getJokeRating(@PathVariable long jokeRatingId) {
        return jokeRatingDbService.getJokeRatingById(jokeRatingId)
                .map(jokeRating -> ResponseEntity.ok(jokeRatingMapper.mapToJokeRatingDto(jokeRating)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{jokeRatingId}")
    public ResponseEntity<Void> deleteJokeRating(@PathVariable long jokeRatingId) {
        jokeRatingDbService.deleteJokeRatingById(jokeRatingId);
        return ResponseEntity.noContent().build();
    }
}
