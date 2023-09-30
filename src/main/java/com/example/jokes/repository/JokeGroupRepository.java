package com.example.jokes.repository;

import com.example.jokes.domain.JokeGroup;
import com.example.jokes.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JokeGroupRepository extends CrudRepository<JokeGroup, Long> {

    @Override
    List<JokeGroup> findAll();

}

