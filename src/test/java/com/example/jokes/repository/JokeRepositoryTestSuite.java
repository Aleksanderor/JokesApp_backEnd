package com.example.jokes.repository;

import com.example.jokes.domain.Joke;
import com.example.jokes.domain.JokeGroup;
import com.example.jokes.domain.User;
import com.example.jokes.service.JokeDbService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@Transactional
public class JokeRepositoryTestSuite {

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JokeGroupRepository jokeGroupRepository;

    @Autowired
    private JokeDbService jokeDbService;

    private User user;
    private JokeGroup group;

    @BeforeEach
    void setUp() {

        user = new User("uzytkownik", LocalDate.parse("2000-01-01"), "email@example.com");
        userRepository.save(user);

        group = new JokeGroup("Funny");
        jokeGroupRepository.save(group);
    }

    @AfterEach
    void cleanUp() {
        userRepository.delete(user);
        jokeGroupRepository.delete(group);
    }

    @Test
    void testAddJokeWithUserAndGroup() {
        // Given
        Joke joke = new Joke();

        // When
        joke.setAuthor(user);
        joke.setJokeGroup(group);
        jokeRepository.save(joke);

        // Then
        Optional<Joke> savedJoke = jokeRepository.findById(joke.getId());
        assertTrue(savedJoke.isPresent());
        assertNull(savedJoke.get().getContent());
        assertEquals(user.getId(), savedJoke.get().getAuthor().getId());
        assertEquals(group.getId(), savedJoke.get().getJokeGroup().getId());

        //CleanUp
        jokeRepository.delete(joke);
    }

    @Test
    void testDeleteJoke() {
        // Given
        Joke joke = new Joke();
        joke.setAuthor(user);
        joke.setJokeGroup(group);
        jokeRepository.save(joke);

        // When
        jokeRepository.delete(joke);

        // Then
        Optional<Joke> deletedJoke = jokeRepository.findById(joke.getId());
        assertFalse(deletedJoke.isPresent());
    }

    @Test
    void testFindJokeById() {
        // Given
        Joke joke = new Joke();
        joke.setAuthor(user);
        joke.setJokeGroup(group);
        jokeRepository.save(joke);

        // When
        Optional<Joke> foundJoke = jokeRepository.findById(joke.getId());

        // Then
        assertTrue(foundJoke.isPresent());
        assertEquals(joke.getId(), foundJoke.get().getId());
    }

    @Test
    void testGetAllJokes() {
        // Given
        Joke joke = new Joke();

        User user2 = new User("kowalski", LocalDate.parse("1985-07-20"), "jan@mail.com");
        userRepository.save(user2);

        // When
        joke.setAuthor(user);
        joke.setJokeGroup(group);
        jokeRepository.save(joke);

        Joke joke2 = new Joke();
        joke2.setAuthor(user2);
        joke2.setJokeGroup(group);
        jokeRepository.save(joke2);

        // Then
        List<Joke> allJokes = jokeDbService.getAllJokes();
        assertEquals(2, allJokes.size());
        assertTrue(allJokes.contains(joke));
        assertTrue(allJokes.contains(joke2));
    }

    @Test
    void testUpdateJoke() {
        // Given
        Joke joke = new Joke();
        joke.setAuthor(user);
        joke.setJokeGroup(group);
        jokeRepository.save(joke);

        // When
        joke.setContent("Updated joke content");
        jokeRepository.save(joke);

        // Then
        Optional<Joke> updatedJoke = jokeRepository.findById(joke.getId());
        assertTrue(updatedJoke.isPresent());
        assertEquals("Updated joke content", updatedJoke.get().getContent());
    }
}

