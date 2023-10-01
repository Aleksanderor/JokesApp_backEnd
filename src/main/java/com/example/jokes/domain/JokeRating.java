package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JOKE_RATING")
public class JokeRating {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "JOKE_RATING_ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "RATING")
    @Min(1)
    @Max(5)
    private Integer rating;

    public JokeRating(User user, Integer rating) {
        this.user = user;
        this.rating = rating;
    }
}
