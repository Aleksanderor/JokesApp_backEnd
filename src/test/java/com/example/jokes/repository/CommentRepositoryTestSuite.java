package com.example.jokes.repository;

import com.example.jokes.domain.Comment;
import com.example.jokes.domain.Joke;
import com.example.jokes.domain.User;
import com.example.jokes.service.CommentDbService;
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
public class CommentRepositoryTestSuite {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentDbService commentDbService;

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
    void testAddCommentToJoke() {
        // Given
        Comment comment = new Comment();
        comment.setJoke(joke);

        // When
        commentRepository.save(comment);

        // Then
        Optional<Comment> savedComment = commentRepository.findById(comment.getId());
        assertTrue(savedComment.isPresent());
        assertEquals(joke.getId(), savedComment.get().getJoke().getId());

        // CleanUp
        commentRepository.delete(comment);
    }

    @Test
    void testDeleteComment() {
        // Given
        Comment comment = new Comment();
        comment.setJoke(joke);
        commentRepository.save(comment);

        // When
        commentRepository.delete(comment);

        // Then
        Optional<Comment> deletedComment = commentRepository.findById(comment.getId());
        assertFalse(deletedComment.isPresent());
    }

    @Test
    void testFindCommentById() {
        // Given
        Comment comment = new Comment();
        comment.setJoke(joke);
        commentRepository.save(comment);

        // When
        Optional<Comment> foundComment = commentRepository.findById(comment.getId());

        // Then
        assertTrue(foundComment.isPresent());
        assertEquals(comment.getId(), foundComment.get().getId());
    }

    @Test
    void testGetAllComments() {
        // Given
        Comment comment = new Comment();
        comment.setJoke(joke);
        commentRepository.save(comment);

        Comment comment2 = new Comment();
        comment2.setJoke(joke);
        commentRepository.save(comment2);

        // Then
        List<Comment> allComments = commentDbService.getAllComments();
        assertEquals(2, allComments.size());
        assertTrue(allComments.contains(comment));
        assertTrue(allComments.contains(comment2));
    }

    @Test
    void testUpdateComment() {
        // Given
        Comment comment = new Comment();
        comment.setJoke(joke);
        commentRepository.save(comment);

        // When
        comment.setCommentContent("Updated comment content");
        commentRepository.save(comment);

        // Then
        Optional<Comment> updatedComment = commentRepository.findById(comment.getId());
        assertTrue(updatedComment.isPresent());
        assertEquals("Updated comment content", updatedComment.get().getCommentContent());
    }
}
