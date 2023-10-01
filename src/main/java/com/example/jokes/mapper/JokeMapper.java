package com.example.jokes.mapper;


import com.example.jokes.domain.Joke;
import com.example.jokes.domain.JokeDto;
import com.example.jokes.exception.GroupNotFoundException;
import com.example.jokes.exception.UserNotFoundException;
import com.example.jokes.service.JokeGroupDbService;
import com.example.jokes.service.JokeRatingAverageCalculatorService;
import com.example.jokes.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JokeMapper {

    private final UserDbService userDbService;
    private final JokeGroupDbService jokeGroupDbService;
    private final JokeGroupMapper jokeGroupMapper;
    private final JokeRatingMapper jokeRatingMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public JokeMapper(UserDbService userDbService, JokeGroupDbService jokeGroupDbService, JokeGroupMapper jokeGroupMapper, JokeRatingMapper jokeRatingMapper, CommentMapper commentMapper) {
        this.userDbService = userDbService;
        this.jokeGroupDbService = jokeGroupDbService;
        this.jokeGroupMapper = jokeGroupMapper;
        this.jokeRatingMapper = jokeRatingMapper;
        this.commentMapper = commentMapper;
    }

    public Joke mapToJoke(final JokeDto jokeDto) throws GroupNotFoundException {
        return new Joke(
                jokeDto.getId(),
                userDbService.getUserById(jokeDto.getAuthor().getId()).orElseThrow(() -> new UserNotFoundException(jokeDto.getAuthor().getId())),
                jokeDto.getCreated(),
                jokeDto.getTags(),
                jokeGroupDbService.getJokeGroupById(jokeDto.getJokeGroup().getId()).orElseThrow(() -> new GroupNotFoundException(jokeDto.getJokeGroup().getId())),
                commentMapper.mapToCommentList(jokeDto.getComments()),
                Collections.emptyList(),
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
                jokeGroupMapper.mapToJokeGroupDto(joke.getJokeGroup()),
                commentMapper.mapToCommentDtoList(joke.getComments()),
                JokeRatingAverageCalculatorService.calculateAverageRatingForJoke(jokeRatingMapper.mapToJokeRatingDtoList(joke.getRatings()))
        );
    }

    public List<JokeDto> mapToJokeDtoList(final List<Joke> productList) {
        return productList.stream().map(this::mapToJokeDto).collect(Collectors.toList());
    }
}
