package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NICK", unique = true)
    private String nick;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;


    public User(String nick, LocalDate birthday, String email) {
        this.nick = nick;
        this.birthday = birthday;
        this.email = email;
    }
}
