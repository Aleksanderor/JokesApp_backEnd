package com.example.jokes.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JokeGroupDto {
    private Long id;
    private String jokeGroupName;
}
