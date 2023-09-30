package com.example.jokes.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JokeTestSuite {

    @Test
    public void testAddition() {
        int a = 2;
        int b = 2;
        int result = a + b;
        int expected = 4;
        assertEquals(expected, result, "Wynik dodawania jest niepoprawny");
    }

}
