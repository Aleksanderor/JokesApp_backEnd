package com.example.jokes.service;

import com.example.jokes.domain.JokeRating;
import com.example.jokes.domain.User;
import com.example.jokes.repository.JokeRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JokeRatingDbService {

    private final JokeRatingRepository jokeRatingRepository;

    @Autowired
    public JokeRatingDbService(JokeRatingRepository jokeRatingRepository) {
        this.jokeRatingRepository = jokeRatingRepository;
    }

    public JokeRating addJokeRating(JokeRating jokeRating) {
        return jokeRatingRepository.save(jokeRating);
    }

    public Optional<JokeRating> getJokeRatingById(long id) {
        return jokeRatingRepository.findById(id);
    }

    public List<JokeRating> getAllJokeRatings() {
        return jokeRatingRepository.findAll();
    }

    public void deleteJokeRatingById(long id) {
        jokeRatingRepository.deleteById(id);
    }

    public List<JokeRating> getAllJokeRatingsByUser(User user) {
        return jokeRatingRepository.findAllByUser(user);
    }
}

