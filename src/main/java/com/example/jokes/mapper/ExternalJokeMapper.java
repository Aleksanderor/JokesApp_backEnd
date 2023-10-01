package com.example.jokes.mapper;

import com.example.jokes.domain.ExternalJokeDto;
import com.example.jokes.domain.Joke;
import com.example.jokes.domain.User;
import com.example.jokes.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExternalJokeMapper {
    @Value("${dadjokes.user.nickname}")
    private String dadJokesUserNick;
    private final UserDbService userDbService;

    @Autowired
    public ExternalJokeMapper(UserDbService userDbService) {
        this.userDbService = userDbService;
    }

    public Joke toJoke(ExternalJokeDto externalJokeDto) {
        User dadJokesUser = userDbService.getUserByNick(dadJokesUserNick);

        return Joke.builder()
                .author(dadJokesUser)
                .content(String.format("%s \n -%s", externalJokeDto.getSetup(), externalJokeDto.getPunchline()))
                .build();
    }
}
