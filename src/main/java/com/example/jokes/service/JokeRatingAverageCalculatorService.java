package com.example.jokes.service;

import com.example.jokes.domain.JokeRatingDto;

import java.util.List;

public class JokeRatingAverageCalculatorService {

    public static double calculateAverageRatingForJoke(List<JokeRatingDto> jokesRatings) {

        if (jokesRatings.isEmpty()) {
            return 0.0;
        }

        double sum = jokesRatings.stream()
                .mapToDouble(JokeRatingDto::getRating)
                .sum();

        return sum / jokesRatings.size();
    }
}