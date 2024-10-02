package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie testMovie;

    @BeforeEach
    void setup() {
        testMovie = new Movie("Harry Potter and the Philosopher's Stone", 2001);
    }

    @Test
    void testConstructor() {
        assertEquals("Harry Potter and the Philosopher's Stone", testMovie.getTitle());
        assertEquals(2001, testMovie.getYear());
        assertEquals("Harry Potter and the Philosopher's Stone 2001", testMovie.toString());
    }

    @Test
    void testRating() {
        testMovie.rating(5);
        assertEquals(5, testMovie.getRating());
    }
}