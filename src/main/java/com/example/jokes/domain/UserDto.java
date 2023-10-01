package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String nick;
    private LocalDate birthday;
    private String email;
}