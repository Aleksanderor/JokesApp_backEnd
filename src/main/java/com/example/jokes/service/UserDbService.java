package com.example.jokes.service;

import com.example.jokes.domain.User;
import com.example.jokes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;


@Service
public class UserDbService {
    private final UserRepository userRepository;

    @Autowired
    public UserDbService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void addUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    public void clear() {
        userRepository.deleteAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByNick(String nick) {
        Optional<User> user = userRepository.findByNick(nick);
        if (user.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
