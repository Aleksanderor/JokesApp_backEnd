package com.example.jokes.jokeNotifier;

import com.example.jokes.domain.Comment;
import com.example.jokes.domain.Joke;

public interface JokeCommentObserver {
    void update(Joke joke, Comment comment);

}
