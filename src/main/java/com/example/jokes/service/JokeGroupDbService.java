package com.example.jokes.service;

import com.example.jokes.domain.JokeGroup;
import com.example.jokes.repository.JokeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JokeGroupDbService {

    private final JokeGroupRepository jokeGroupRepository;

    @Autowired
    public JokeGroupDbService(JokeGroupRepository jokeGroupRepository) {
        this.jokeGroupRepository = jokeGroupRepository;
    }

    public JokeGroup addJokeGroup(JokeGroup jokeGroup) {

        return jokeGroupRepository.save(jokeGroup);
    }

    public Optional<JokeGroup> getJokeGroupById(Long id) {

        return jokeGroupRepository.findById(id);
    }

    public List<JokeGroup> getAllJokeGroups() {

        return jokeGroupRepository.findAll();
    }

    public void deleteJokeGroupById(Long id) {
        jokeGroupRepository.deleteById(id);
    }

}