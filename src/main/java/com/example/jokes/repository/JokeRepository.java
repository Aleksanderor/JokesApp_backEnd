package com.example.jokes.repository;

import com.example.jokes.domain.Joke;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

public interface JokeRepository extends CrudRepository<Joke, Long> {

    @Override
    List<Joke> findAll();

    List<Joke> findAllByAuthor(String author);

}
