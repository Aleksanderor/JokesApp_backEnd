package com.example.jokes.service;

import com.example.jokes.domain.Joke;
import com.example.jokes.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JokeDbService {

    private final JokeRepository jokeRepository;

    @Autowired
    public JokeDbService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }
    public Joke addJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    public Optional<Joke> getJokeById(Long id) {
        return jokeRepository.findById(id);
    }

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public List<Joke> getJokesByAuthor(String author) {
        return jokeRepository.findAllByAuthor(author);
    }

    public void deleteJokeById(Long id) {
        jokeRepository.deleteById(id);
    }
}
