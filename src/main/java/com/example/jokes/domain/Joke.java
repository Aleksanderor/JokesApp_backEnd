package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Table(name = "JOKE")
public class Joke {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "JOKE_ID", unique = true)
    private long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User author;

    @Column(name = "CREATED")
    private LocalDateTime created;

    @Column(name = "TAGS")
    private String tags;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "JOKE_GROUP_ID")
    private JokeGroup jokeGroup;

    @OneToMany
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    private List<JokeRating> ratings = new ArrayList<>();
    @Column(name = "CONTENT")
    private String content;

    @PrePersist
    void setCreatedAt() {
        this.created = LocalDateTime.now();
    }

    public Joke(String content) {
        this.content = content;
    }

    public Joke(User author, String tags, JokeGroup jokeGroup, List<Comment> comments, String content) {
        this.author = author;
        this.tags = tags;
        this.jokeGroup = jokeGroup;
        this.comments = comments;
        this.content = content;
    }
}

