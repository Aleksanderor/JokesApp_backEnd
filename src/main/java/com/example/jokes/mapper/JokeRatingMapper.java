package com.example.jokes.mapper;

import com.example.jokes.domain.JokeRating;
import com.example.jokes.domain.JokeRatingDto;
import com.example.jokes.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JokeRatingMapper {
    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @Autowired
    public JokeRatingMapper(UserDbService userDbService, UserMapper userMapper) {
        this.userDbService = userDbService;
        this.userMapper = userMapper;
    }

    public JokeRatingDto mapToJokeRatingDto(final JokeRating jokeRating) {
        return new JokeRatingDto(
                jokeRating.getId(),
                jokeRating.getRating(),
                userMapper.mapToUserDto(jokeRating.getUser())
        );
    }

    public JokeRating mapToJokeRating(final JokeRatingDto jokeRatingDto) {
        return new JokeRating(
                jokeRatingDto.getId(),
                userDbService.getUserByNick(jokeRatingDto.getUser().getNick()),

                jokeRatingDto.getRating()
        );
    }

    public List<JokeRatingDto> mapToJokeRatingDtoList(final List<JokeRating> jokeRatingList) {
        return jokeRatingList.stream().map(this::mapToJokeRatingDto).collect(Collectors.toList());
    }
}
