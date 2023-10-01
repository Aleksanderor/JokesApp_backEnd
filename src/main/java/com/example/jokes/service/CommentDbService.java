package com.example.jokes.service;

import com.example.jokes.domain.Comment;
import com.example.jokes.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentDbService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentDbService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }

}
