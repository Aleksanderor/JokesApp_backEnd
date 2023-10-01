package com.example.jokes.service;

import com.example.jokes.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class UserInitializer {
    @Value("${dadjokes.user.nickname}")
    private String externalUserNick;
    private final UserDbService userDbService;

    @Autowired
    public UserInitializer(UserDbService userDbService) {
        this.userDbService = userDbService;
    }

    @PostConstruct
    void importExternalUser() {
        userDbService.addUser(new User(externalUserNick, LocalDate.of(1900, 1, 1), "dad-jokes.p.rapidapi.com"));
    }
}
