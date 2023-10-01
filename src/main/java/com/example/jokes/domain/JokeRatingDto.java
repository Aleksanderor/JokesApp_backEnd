package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JokeRatingDto {
    private Long id;
    private Integer rating;
    private UserDto user;
}
