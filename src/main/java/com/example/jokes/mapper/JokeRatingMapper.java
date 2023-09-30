package com.example.jokes.mapper;

import com.example.jokes.domain.JokeRating;
import com.example.jokes.domain.JokeRatingDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JokeRatingMapper {

    public JokeRatingDto mapToJokeRatingDto(final JokeRating jokeRating) {
        return new JokeRatingDto(
                jokeRating.getId(),
                jokeRating.getRating()
        );
    }

    public JokeRating mapToJokeRating(final JokeRatingDto jokeRatingDto) {
        return new JokeRating(
                jokeRatingDto.getId(),
                jokeRatingDto.getRating()
        );
    }

    public List<JokeRatingDto> mapToJokeRatingDtoList(final List<JokeRating> jokeRatingList) {
        return jokeRatingList.stream().map(this::mapToJokeRatingDto).collect(Collectors.toList());
    }
}
