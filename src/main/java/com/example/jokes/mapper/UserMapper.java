package com.example.jokes.mapper;

import com.example.jokes.domain.User;
import com.example.jokes.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getNick(),
                userDto.getName(),
                userDto.getBirthday(),
                userDto.getEmail()
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getNick(),
                user.getName(),
                user.getBirthday(),
                user.getEmail()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }
}
