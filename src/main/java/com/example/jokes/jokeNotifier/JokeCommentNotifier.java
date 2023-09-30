package com.example.jokes.jokeNotifier;

import com.example.jokes.domain.Comment;
import com.example.jokes.domain.Joke;

import java.util.ArrayList;
import java.util.List;

public class JokeCommentNotifier {
    private List<JokeCommentObserver> observers = new ArrayList<>();

        public void addObserver(JokeCommentObserver observer) {
            observers.add(observer);
        }

        public void removeObserver(JokeCommentObserver observer) {
            observers.remove(observer);
        }

        public void notifyObservers(Joke joke, Comment comment) {
            for (JokeCommentObserver observer : observers) {
                observer.update(joke, comment);
            }
        }
}
