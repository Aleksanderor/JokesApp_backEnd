package com.example.jokes.repository;

import com.example.jokes.domain.User;
import com.example.jokes.service.UserDbService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDbService userDbService;

    @AfterEach
    void cleanUp() {

        userRepository.deleteAll();
    }

    @Test
    void addUser() {

        //Given
        User user = new User("Johnson", LocalDate.of(1985, 5, 20), "alice@example.com");

        // When
        userRepository.save(user);

        // Then
        assertTrue(userRepository.existsById(user.getId()));

    }

    @Test
    void deleteByIdUserTest() {

        //Given
        User user = new User("Johnson", LocalDate.of(1985, 5, 20), "alice@example.com");

        //When
        userRepository.save(user);
        userRepository.deleteById(user.getId());

        //Then
        assertFalse(userRepository.existsById(user.getId()));
    }

    @Test
    void findByUserIDTest() {
        //Given
        User user = new User("Johnson", LocalDate.of(1985, 5, 20), "alice@example.com");

        //When
        userRepository.save(user);

        //Then
        Long id = user.getId();
        assertTrue(userRepository.findById(id).isPresent());

    }

    @Test
    void findAllUsers() {

        //Given
        User user = new User("Johnson", LocalDate.of(1985, 5, 20), "alice@example.com");
        User user2 = new User("Johnson", LocalDate.of(1985, 5, 20), "user2@example.com");

        userRepository.save(user);
        userRepository.save(user2);

        //When
        List<User> users = userDbService.getAllUsers();

        //Then
        assertEquals(2, users.size());
    }

}