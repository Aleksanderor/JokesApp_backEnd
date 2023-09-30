package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {

    private long id;
    private Joke joke;

    private User author;
    private String commentContent;
    private LocalDateTime created;

    public CommentDto(Long id, Joke joke, User author, String commentContent, LocalDateTime created) {
        this.id = id;
        this.joke = joke;
        this.author=author;
        this.commentContent = commentContent;
        this.created = created;
    }

}
