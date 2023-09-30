//package com.example.jokes.repository;
//
//import com.example.jokes.domain.Joke;
//import com.example.jokes.domain.JokeRating;
//import com.example.jokes.domain.User;
//import com.example.jokes.service.JokeRatingAverageCalculatorService;
//import com.example.jokes.service.JokeRatingDbService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class JokeRatingAverageCalculatorServiceTest {
//
//    @Autowired
//    private JokeRatingAverageCalculatorService calculatorService;
//
//    @Autowired
//    private JokeRatingDbService jokeRatingDbService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JokeRepository jokeRepository;
//
//    @Autowired
//    private JokeRatingRepository jokeRatingRepository;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void testCalculateAverageRatingForJoke() {
//        // Given
//        User user = new User("jan", "kowalski", LocalDate.parse("1985-07-20"), "jan@mail.com");
//        User user2 = new User("jan", "kowalski", LocalDate.parse("1985-07-20"), "jan@mail.com");
//
//        userRepository.save(user);
//        userRepository.save(user2);
//
//        Joke joke69 = new Joke();
//        joke69.setAuthor(user);
//        jokeRepository.save(joke69);
//
//        joke69.getRatings().clear();
//
//
//        JokeRating rating2 = new JokeRating(user2, joke69, 4);
//        joke69.getRatings().add(rating2); // Dodaj ocenę do listy w obiekcie Joke
//
//        jokeRatingRepository.save(rating2);
//
//        // When
//        double averageRating = calculatorService.calculateAverageRatingForJoke(joke69);
//
//        // Then
//        //assertEquals(4.0, averageRating, 0.01);
//
//        // Additional: Check ratings for joke69
//        System.out.println("Fields in JokeRating:");
//        System.out.println("Joke: " + rating2.getJoke());
//        System.out.println("User: " + rating2.getUser());
//        System.out.println("JokeRatings: " + rating2.getJokeRatings());
//
//        System.out.println("Fields in Joke:");
//        System.out.println("Joke: " + joke69);
//        System.out.println("User: " + joke69.getAuthor());
//        System.out.println("JokeRatings: " + joke69.getRatings());
//        }
//    }
//
////    @Test
////    void testCalculateAverageRatingForUserJokes() {
////
////        // Given
////        User user1 = new User();
////        User user2 = new User();
////        List<JokeRating> ratings = new ArrayList<>();
////        ratings.add(new JokeRating(user, new Joke(), 5));
////        ratings.add(new JokeRating(user, new Joke(), 4));
////        ratings.add(new JokeRating(user, new Joke(), 3));
////        when(jokeRatingDbService.getAllJokeRatingsByUser(user)).thenReturn(ratings);
////
////        // When
////        double averageRating = calculatorService.calculateAverageRatingForUserJokes(user);
////
////        // Then
////        assertEquals(4.0, averageRating, 0.01);
////    }
////
//
