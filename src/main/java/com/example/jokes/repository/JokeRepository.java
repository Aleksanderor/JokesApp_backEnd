package com.example.jokes.repository;

import com.example.jokes.domain.Joke;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JokeRepository extends CrudRepository<Joke, Long> {
    @Query("SELECT j FROM Joke j WHERE j.author.nick = :nick")
    List<Joke> findJokesByAuthorNick(@Param("nick") String nick);
}
