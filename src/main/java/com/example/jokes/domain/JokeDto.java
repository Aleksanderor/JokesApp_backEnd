package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class JokeDto {
    private Long id;
    private User author;

    private String content;
    private LocalDateTime created;
    private String tags;
    private JokeGroup jokeGroup;
    private List<Comment> comments;
    private List<JokeRating> ratings;
}
