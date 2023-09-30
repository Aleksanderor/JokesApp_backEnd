package com.example.jokes.repository;

import com.example.jokes.domain.Joke;
import com.example.jokes.domain.JokeGroup;
import com.example.jokes.service.JokeGroupDbService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class JokeGroupRepositoryTestSuite {

    @Autowired
    private JokeGroupRepository jokeGroupRepository;

    @Autowired
    JokeGroupDbService jokeGroupDbService;
    private JokeGroup jokeGroup;

    @BeforeEach
    void setUp() {
        jokeGroup = new JokeGroup( "Funny");
        jokeGroupRepository.save(jokeGroup);
    }

    @AfterEach
    void cleanUp() {
        jokeGroupRepository.delete(jokeGroup);
    }

    @Test
    void testAddJokeGroup() {
        //Given
        //When

        //Then
        assertTrue(jokeGroupRepository.existsById(jokeGroup.getId()));


    }

    @Test
    void testFindJokeGroupById() {
        //Given
        //When

        //Then
        Long id = jokeGroup.getId();
        assertTrue(jokeGroupRepository.findById(id).isPresent());

    }

    @Test
    void testFindAllJokeGroups() {
        //Given
        JokeGroup jokeGroup2 = new JokeGroup( "sad");
        jokeGroupRepository.save(jokeGroup2);

        //When
        List<JokeGroup> allGroups = jokeGroupDbService.getAllJokeGroups();

        //Then
        //assertEquals(2, allGroups.size());
        assertTrue(allGroups.contains(jokeGroup));
        assertTrue(allGroups.contains(jokeGroup2));

        //CleanUp
        jokeGroupRepository.delete(jokeGroup2);


    }

    @Test
    void testUpdateJokeGroup() {
        //Given
        //When
        jokeGroup.setName("not funny");
        jokeGroupRepository.save(jokeGroup);
        JokeGroup updatedGroup = jokeGroupRepository.findById(jokeGroup.getId()).orElse(null);
        //Then
        assertNotNull(updatedGroup);
        assertEquals("not funny", updatedGroup.getName());
    }

    @Test
    void testDeleteJokeGroup() {
        //Given
        //When
        jokeGroupRepository.delete(jokeGroup);

        //Then
        assertFalse(jokeGroupRepository.existsById(jokeGroup.getId()));

    }
}