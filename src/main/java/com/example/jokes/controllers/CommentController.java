package com.example.jokes.controllers;


import com.example.jokes.domain.Comment;
import com.example.jokes.domain.CommentDto;
import com.example.jokes.mapper.CommentMapper;
import com.example.jokes.service.CommentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {

    private final CommentMapper commentMapper;
    private final CommentDbService commentDbService;

    @Autowired
    public CommentController(CommentDbService commentDbService, CommentMapper commentMapper) {
        this.commentDbService = commentDbService;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.mapToComment(commentDto);
        commentDbService.addComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments() {
        List<CommentDto> comments = commentMapper.mapToCommentDtoList(commentDbService.getAllComments());
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable long commentId) {
        return commentDbService.getCommentById(commentId)
                .map(comment -> ResponseEntity.ok(commentMapper.mapToCommentDto(comment)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable long commentId) {
        commentDbService.deleteCommentById(commentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long commentId, @RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.mapToComment(commentDto);
        comment.setId(commentId);
        return ResponseEntity.ok(commentMapper.mapToCommentDto(commentDbService.addComment(comment)));
    }
}
