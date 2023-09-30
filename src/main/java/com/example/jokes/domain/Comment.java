package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;

    @ManyToOne
    @JoinColumn(name = "JOKE_ID")
    private Joke joke;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @Column(name = "CREATED")
    private LocalDateTime created;

    public Comment (Long id, Joke joke, User author, String commentContent, LocalDateTime created) {
        this.id = id;
        this.joke = joke;
        this.author=author;
        this.commentContent = commentContent;
        this.created = created;
    }
}