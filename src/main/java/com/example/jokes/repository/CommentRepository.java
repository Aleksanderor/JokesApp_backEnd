package com.example.jokes.repository;

import com.example.jokes.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Override
    List<Comment> findAll();

    @Override
    Comment save(Comment comment);

    @Override
    Optional<Comment> findById(Long id);

    @Override
    void deleteById(Long id);
}

