package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JOKEGROUP")
public class JokeGroup {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    @Column(name="JOKE_GROUP_ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    public JokeGroup(String name) {
        this.name = name;
    }
}
