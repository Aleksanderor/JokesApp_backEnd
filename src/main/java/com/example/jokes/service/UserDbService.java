package com.example.jokes.service;

import com.example.jokes.domain.Joke;
import com.example.jokes.domain.User;
import com.example.jokes.repository.JokeRepository;
import com.example.jokes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserDbService {


    private final UserRepository userRepository;
    private final JokeRepository jokeRepository;

    @Autowired
    public UserDbService(UserRepository userRepository, JokeRepository jokeRepository) {
        this.userRepository = userRepository;
        this.jokeRepository = jokeRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByNick(String nick) {
        return userRepository.findByNick(nick);
    }

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
