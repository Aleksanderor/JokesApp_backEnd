package com.example.jokes.service;

import com.example.jokes.domain.Joke;
import com.example.jokes.domain.JokeRating;
import com.example.jokes.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeRatingAverageCalculatorService {

    private final JokeRatingDbService jokeRatingDbService;

    @Autowired
    public JokeRatingAverageCalculatorService(JokeRatingDbService jokeRatingDbService) {
        this.jokeRatingDbService = jokeRatingDbService;
    }

    public double calculateAverageRatingForJoke(Joke joke) {

        List<JokeRating> ratings = jokeRatingDbService.getAllJokeRatings();

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = ratings.stream()
                .mapToDouble(JokeRating::getRating)
                .sum();

        return sum / ratings.size();
    }

    public double calculateAverageRatingForUserJokes(User user) {
        List<JokeRating> ratings = jokeRatingDbService.getAllJokeRatingsByUser(user);

        if (ratings.isEmpty()) {
            return 0.0; // Brak ocen
        }

        double sum = ratings.stream()
                .mapToDouble(JokeRating::getRating)
                .sum();

        return sum / ratings.size();
    }
}