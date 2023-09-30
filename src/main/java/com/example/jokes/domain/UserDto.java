package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String nick;
    private String name;
    private LocalDate birthday;
    private String email;
    private List<Joke> userJokes = new ArrayList<>();

    public UserDto(Long id, String nick, String name, LocalDate birthday, String email) {
        this.id = id;
        this.nick = nick;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.userJokes = new ArrayList<>();
    }
}