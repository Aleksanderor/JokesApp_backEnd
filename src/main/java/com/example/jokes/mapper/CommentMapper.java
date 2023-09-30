package com.example.jokes.mapper;

import com.example.jokes.domain.Comment;
import com.example.jokes.domain.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentMapper {
    public Comment mapToComment(final CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                commentDto.getJoke(),
                commentDto.getAuthor(),
                commentDto.getCommentContent(),
                commentDto.getCreated()
                );

    }

    public CommentDto mapToCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getJoke(),
                comment.getAuthor(),
                comment.getCommentContent(),
                comment.getCreated()
        );
    }

    public List<CommentDto> mapToCommentDtoList(final List<Comment> commentList) {
        return commentList.stream().map(this::mapToCommentDto).collect(Collectors.toList());
    }
}

