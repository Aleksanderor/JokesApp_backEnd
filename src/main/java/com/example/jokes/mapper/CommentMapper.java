package com.example.jokes.mapper;

import com.example.jokes.domain.Comment;
import com.example.jokes.domain.CommentDto;
import com.example.jokes.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentMapper {
    private final UserDbService userDbService;

    @Autowired
    public CommentMapper(UserDbService userDbService) {
        this.userDbService = userDbService;
    }

    public Comment mapToComment(final CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                userDbService.getUserByNick(commentDto.getAuthor()),
                commentDto.getCommentContent(),
                commentDto.getCreated()
                );
    }

    public CommentDto mapToCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getAuthor().getNick(),
                comment.getCommentContent(),
                comment.getCreated()
        );
    }

    public List<CommentDto> mapToCommentDtoList(final List<Comment> commentList) {
        return commentList.stream().map(this::mapToCommentDto).collect(Collectors.toList());
    }

    public List<Comment> mapToCommentList(final List<CommentDto> commentList) {
        return commentList.stream().map(this::mapToComment).collect(Collectors.toList());
    }
}

