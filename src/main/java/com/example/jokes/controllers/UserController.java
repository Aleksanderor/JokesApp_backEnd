package com.example.jokes.controllers;


import com.example.jokes.domain.UserDto;
import com.example.jokes.mapper.UserMapper;
import com.example.jokes.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/users")
@CrossOrigin("*")
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserDbService userDbService, UserMapper userMapper) {
        this.userDbService = userDbService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userDbService.addUser(userMapper.mapToUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userMapper.mapToUserDtoList(userDbService.getAllUsers());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return userDbService.getUserById(userId)
                .map(user -> ResponseEntity.ok(userMapper.mapToUserDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/byNick/{nick}")
    public ResponseEntity<UserDto> getUserByNick(@PathVariable String nick) {
        return userDbService.getUserByNick(nick)
                .map(user -> ResponseEntity.ok(userMapper.mapToUserDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userDbService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}