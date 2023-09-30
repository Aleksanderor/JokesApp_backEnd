package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="JOKE_RATING")
public class JokeRating {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="JOKE_RATING_ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "JOKE_ID")
    private Joke joke;

    @Column(name = "RATING")
    @Min(1)
    @Max(5)
    private Integer rating;


    public JokeRating(Long id, Integer raitng){
        this.rating = raitng;
        this.id = id;
    }

    public JokeRating(User user, Joke joke, Integer raitng){
        this.user = user;
        this.joke = joke;
        this.rating = raitng;
    }
}
