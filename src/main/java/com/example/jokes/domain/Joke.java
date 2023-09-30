package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="JOKE")
public class Joke {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="JOKE_ID", unique = true)
    private long id;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;
    @Column(name="CREATED")
    private LocalDateTime created;

    @Column(name = "TAGS")
    private String tags;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "JOKE_GROUP_ID")
    private JokeGroup jokeGroup;

    @OneToMany(mappedBy = "joke")
    private List<Comment> comments;

    @OneToMany(mappedBy = "joke", cascade = CascadeType.ALL)
    private List<JokeRating> ratings = new ArrayList<>();
    @Column(name = "CONTENT")
    private String content;

    public Joke(String content){
        this.content = content;
    }

    @ManyToMany(mappedBy = "observedJokes")
    private List<User> observers = new ArrayList<>();



}

