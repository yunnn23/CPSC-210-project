package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(String title, int year, Movie movie) {
        assertEquals(title, movie.getTitle());
        assertEquals(year, movie.getYear());
    }
}
