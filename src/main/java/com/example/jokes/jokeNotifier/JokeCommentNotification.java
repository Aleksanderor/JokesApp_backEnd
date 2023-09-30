package com.example.jokes.jokeNotifier;

import com.example.jokes.domain.Comment;
import com.example.jokes.domain.Joke;

import java.util.ArrayList;
import java.util.List;

public class JokeCommentNotification {
    private Joke joke;
    private Comment comment;

    public JokeCommentNotification(Joke joke, Comment comment) {
        this.joke = joke;
        this.comment = comment;
    }

    public Joke getJoke() {
        return joke;
    }

    public Comment getComment() {
        return comment;
    }
}
