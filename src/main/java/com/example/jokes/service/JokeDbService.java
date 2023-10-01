package com.example.jokes.service;

import com.example.jokes.domain.Joke;
import com.example.jokes.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class JokeDbService {

    private final JokeRepository jokeRepository;

    @Autowired
    public JokeDbService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }
    public Joke addJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    public Joke getJokeById(Long id) {
        Optional<Joke> jokeOpt = jokeRepository.findById(id);
        if(jokeOpt.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return jokeOpt.get();
    }

    public List<Joke> getAllJokes() {
        return (List<Joke>) jokeRepository.findAll();
    }

    public List<Joke>  getByAuthorNick(String nick) {
        return jokeRepository.findJokesByAuthorNick(nick);
    }

    public void deleteJokeById(Long id) {
        jokeRepository.deleteById(id);
    }

    public void clear(){
        jokeRepository.deleteAll();
    }
}
