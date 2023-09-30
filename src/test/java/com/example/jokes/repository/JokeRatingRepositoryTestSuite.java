package com.example.jokes.repository;

import com.example.jokes.domain.Joke;
import com.example.jokes.domain.JokeRating;
import com.example.jokes.domain.User;
import com.example.jokes.service.JokeRatingDbService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JokeRatingRepositoryTestSuite {

    @Autowired
    private JokeRatingRepository jokeRatingRepository;

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JokeRatingDbService jokeRatingDbService;

    private User user;
    private Joke joke;

    @BeforeEach
    void setUp() {
        user = new User("jakis", "uzytkownik", LocalDate.parse("2000-01-01"), "email@example.com");
        userRepository.save(user);

        joke = new Joke();
        joke.setAuthor(user);
        jokeRepository.save(joke);
    }

    @AfterEach
    void cleanUp() {
        userRepository.delete(user);
        jokeRepository.delete(joke);
    }

    @Test
    void testAddJokeRatingToJoke() {
        // Given
        JokeRating jokeRating = new JokeRating();
        jokeRating.setJoke(joke);

        // When
        jokeRatingRepository.save(jokeRating);

        // Then
        Optional<JokeRating> savedJokeRating = jokeRatingRepository.findById(jokeRating.getId());
        assertTrue(savedJokeRating.isPresent());
        assertEquals(joke.getId(), savedJokeRating.get().getJoke().getId());

        // CleanUp
        jokeRatingRepository.delete(jokeRating);
    }

    @Test
    void testDeleteJokeRating() {
        // Given
        JokeRating jokeRating = new JokeRating();
        jokeRating.setJoke(joke);
        jokeRatingRepository.save(jokeRating);

        // When
        jokeRatingRepository.delete(jokeRating);

        // Then
        Optional<JokeRating> deletedJokeRating = jokeRatingRepository.findById(jokeRating.getId());
        assertFalse(deletedJokeRating.isPresent());
    }

    @Test
    void testFindJokeRatingById() {
        // Given
        JokeRating jokeRating = new JokeRating();
        jokeRating.setJoke(joke);
        jokeRatingRepository.save(jokeRating);

        // When
        Optional<JokeRating> foundJokeRating = jokeRatingRepository.findById(jokeRating.getId());

        // Then
        assertTrue(foundJokeRating.isPresent());
        assertEquals(jokeRating.getId(), foundJokeRating.get().getId());
    }

    @Test
    void testGetAllJokeRatings() {
        // Given
        JokeRating jokeRating = new JokeRating();
        jokeRating.setJoke(joke);
        jokeRatingRepository.save(jokeRating);

        JokeRating jokeRating2 = new JokeRating();
        jokeRating2.setJoke(joke);
        jokeRatingRepository.save(jokeRating2);

        // Then
        List<JokeRating> allJokeRatings = jokeRatingDbService.getAllJokeRatings();
        assertEquals(2, allJokeRatings.size());
        assertTrue(allJokeRatings.contains(jokeRating));
        assertTrue(allJokeRatings.contains(jokeRating2));
    }

    @Test
    void testUpdateJokeRating() {
        // Given
        JokeRating jokeRating = new JokeRating();
        jokeRating.setJoke(joke);
        jokeRatingRepository.save(jokeRating);

        // When
        jokeRating.setRating(5); // Updated rating value
        jokeRatingRepository.save(jokeRating);

        // Then
        Optional<JokeRating> updatedJokeRating = jokeRatingRepository.findById(jokeRating.getId());
        assertTrue(updatedJokeRating.isPresent());
        assertEquals(5, updatedJokeRating.get().getRating());
    }

    @Test
    public void testFindAllByUser() {
        // Given
        User user1 = new User("John", "Doe", LocalDate.of(1990, 1, 1), "john@example.com");
        User user2 = new User("Alice", "Smith", LocalDate.of(1985, 5, 5), "alice@example.com");
        User user3 = new User("Bob", "Johnson", LocalDate.of(1988, 7, 10), "bob@example.com");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Joke joke = new Joke("Sample joke content");
        joke.setAuthor(user1);
        jokeRepository.save(joke);

        JokeRating rating1 = new JokeRating(user1, joke, 5);
        JokeRating rating2 = new JokeRating(user2, joke, 4);
        JokeRating rating3 = new JokeRating(user3, joke, 3);

        jokeRatingRepository.save(rating1);
        jokeRatingRepository.save(rating2);
        jokeRatingRepository.save(rating3);

        /// When
        List<JokeRating> userRatings = jokeRatingRepository.findAllByUser(user1);

        // Then
        assertEquals(3, userRatings.size());
    }
}


