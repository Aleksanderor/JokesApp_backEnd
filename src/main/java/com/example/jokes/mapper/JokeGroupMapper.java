package com.example.jokes.mapper;

import com.example.jokes.domain.JokeGroup;
import com.example.jokes.domain.JokeGroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JokeGroupMapper {

    public JokeGroupDto mapToJokeGroupDto(final JokeGroup jokeGroup) {
        return new JokeGroupDto(
                jokeGroup.getId(),
                jokeGroup.getName()
        );
    }

    public JokeGroup maptoJokeGroup(final JokeGroupDto jokeGroupDto){
        return new JokeGroup(
                jokeGroupDto.getName()
        );
    }

    public List<JokeGroupDto> mapToJokeGroupDtoList(final List<JokeGroup> jokeGroupList) {
        return jokeGroupList.stream().map(this::mapToJokeGroupDto).collect(Collectors.toList());
    }
}
