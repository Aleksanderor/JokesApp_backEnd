package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {

    private long id;
    private String author;
    private String commentContent;
    private LocalDateTime created;

    public CommentDto(Long id, String author, String commentContent, LocalDateTime created) {
        this.id = id;
        this.author=author;
        this.commentContent = commentContent;
        this.created = created;
    }

}
