package com.example.jokes.repository;

import com.example.jokes.domain.Joke;
import com.example.jokes.domain.JokeRating;
import com.example.jokes.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JokeRatingRepository extends CrudRepository<JokeRating, Long> {

    @Override
    List<JokeRating> findAll();

    @Override
    JokeRating save(JokeRating jokeRating);

    @Override
    Optional<JokeRating> findById(Long id);

    @Override
    void deleteById(Long id);

    List<JokeRating> findAllByUser(User user);
    List<JokeRating> findAllByUserAndJoke(User user, Joke joke);



}

