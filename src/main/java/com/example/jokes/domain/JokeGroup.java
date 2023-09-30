package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JOKEGROUP")
public class JokeGroup {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="JOKE_GROUP_ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "jokeGroup")
    private List<Joke> jokes;

    public JokeGroup(String name) {
        this.id = id;
        this.name = name;
        this.jokes = new ArrayList<>();
    }
}
