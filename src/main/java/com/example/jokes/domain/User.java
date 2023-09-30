package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="USER_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name="NICK", unique = true)
    private String nick;

    @Column(name="NAME")
    private String name;

    @Column(name="BIRTHDAY")
    private LocalDate birthday;

    @NotNull
    @Column(name="EMAIL", unique = true)
    private String email;

    @OneToMany(mappedBy = "author")
    private List <Joke> userJokes = new ArrayList<>();

    public User(String nick, String name, LocalDate birthday, String email) {
        this.nick = nick;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.userJokes = new ArrayList<>();
    }
}
