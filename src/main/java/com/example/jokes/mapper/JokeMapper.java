package com.example.jokes.mapper;


import com.example.jokes.domain.Joke;
import com.example.jokes.domain.JokeDto;
import com.example.jokes.exception.GroupNotFoundException;
import com.example.jokes.exception.UserNotFoundException;
import com.example.jokes.service.JokeGroupDbService;
import com.example.jokes.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JokeMapper {

    private final UserDbService userDbService;
    private final JokeGroupDbService jokeGroupDbService;

    @Autowired
    public JokeMapper(UserDbService userDbService, JokeGroupDbService jokeGroupDbService) {
        this.userDbService = userDbService;
        this.jokeGroupDbService = jokeGroupDbService;
    }

    public Joke mapToJoke(final JokeDto jokeDto) throws GroupNotFoundException {
        return new Joke(
                jokeDto.getId(),
                userDbService.getUserById(jokeDto.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException(jokeDto.getAuthor().getId())),
                jokeDto.getCreated(),
                jokeDto.getTags(),
                jokeGroupDbService.getJokeGroupById(jokeDto.getJokeGroup().getId()).orElseThrow(() -> new GroupNotFoundException(jokeDto.getJokeGroup().getId())),
                jokeDto.getComments(),
                jokeDto.getRatings(),
                jokeDto.getContent()
        );
    }

    public JokeDto mapToJokeDto(Joke joke) {
        return new JokeDto(
                joke.getId(),
                joke.getAuthor(),
                joke.getContent(),
                joke.getCreated(),
                joke.getTags(),
                joke.getJokeGroup(),
                joke.getComments(),
                joke.getRatings()
        );
    }

    public List<JokeDto> mapToJokeDtoList(final List<Joke> productList) {
        return productList.stream().map(this::mapToJokeDto).collect(Collectors.toList());
    }
}
