package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WatchedListTest {
    private WatchedList myWatchedList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    void setup() {
        myWatchedList = new WatchedList();
        movie1 = new Movie("Interstellar", 2014);
        movie2 = new Movie("The Godfather", 1972);
        movie3 = new Movie("Inception", 2010);
    }

    @Test
    void testConstructor() {
        assertEquals(0, myWatchedList.getWatchedMovies().size());
        assertEquals(0, myWatchedList.getNumWatchedMovies());
    }

    @Test
    void testAddWatchedMovie() {
        myWatchedList.addWatchedMovie(movie1);

        List<Movie> watchedMovieList = myWatchedList.getWatchedMovies();

        assertTrue(watchedMovieList.contains(movie1));
        assertEquals(movie1, watchedMovieList.get(0));
        assertEquals(1, watchedMovieList.size());
        assertEquals(1, myWatchedList.getNumWatchedMovies());
    }

    @Test
    void testAddWatchedMovieMultipleTimes() {
        myWatchedList.addWatchedMovie(movie1);
        myWatchedList.addWatchedMovie(movie2);
        myWatchedList.addWatchedMovie(movie3);

        List<Movie> watchedMovieList = myWatchedList.getWatchedMovies();

        assertTrue(watchedMovieList.contains(movie1));
        assertTrue(watchedMovieList.contains(movie2));
        assertTrue(watchedMovieList.contains(movie3));

        assertEquals(movie1, watchedMovieList.get(0));
        assertEquals(movie2, watchedMovieList.get(1));
        assertEquals(movie3, watchedMovieList.get(2));

        assertEquals(3, watchedMovieList.size());
        assertEquals(3, myWatchedList.getNumWatchedMovies());
    }

    @Test
    void testRemoveWatchedMovie() {
        myWatchedList.addWatchedMovie(movie1);
        myWatchedList.removeWatchedMovie(movie1);

        List<Movie> watchedMovieList = myWatchedList.getWatchedMovies();

        assertFalse(watchedMovieList.contains(movie1));
        assertEquals(0, watchedMovieList.size());
        assertEquals(0, myWatchedList.getNumWatchedMovies());
    }

    @Test
    void testRemoveWatchedMovieMultipleTimes() {
        myWatchedList.addWatchedMovie(movie1);
        myWatchedList.addWatchedMovie(movie2);
        myWatchedList.addWatchedMovie(movie3);
        myWatchedList.removeWatchedMovie(movie1);
        myWatchedList.removeWatchedMovie(movie2);

        List<Movie> watchedMovieList = myWatchedList.getWatchedMovies();

        assertFalse(watchedMovieList.contains(movie1));
        assertFalse(watchedMovieList.contains(movie2));
        assertTrue(watchedMovieList.contains(movie3));
        assertEquals(movie3, watchedMovieList.get(0));
        assertEquals(1, watchedMovieList.size());
        assertEquals(1, myWatchedList.getNumWatchedMovies());
    }

    @Test
    void testRemoveMovieToFail() {
        myWatchedList.addWatchedMovie(movie1);
        myWatchedList.addWatchedMovie(movie2);
        myWatchedList.addWatchedMovie(movie3);
        myWatchedList.removeMovieFromList("Harry Potter");

        List<Movie> movies = myWatchedList.getWatchedMovies();

        assertEquals(3, movies.size());
        assertTrue(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
        assertTrue(movies.contains(movie3));
        assertEquals(movie1, movies.get(0));
        assertEquals(movie2, movies.get(1));
        assertEquals(movie3, movies.get(2));
        assertEquals(3, myWatchedList.getNumWatchedMovies());
    }

    @Test
    void testRemoveMovieFromList() {
        myWatchedList.addWatchedMovie(movie1);
        myWatchedList.addWatchedMovie(movie2);
        myWatchedList.addWatchedMovie(movie3);
        myWatchedList.removeMovieFromList("Interstellar");

        List<Movie> movies = myWatchedList.getWatchedMovies();

        assertEquals(2, movies.size());
        assertFalse(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
        assertTrue(movies.contains(movie3));
        assertEquals(movie2, movies.get(0));
        assertEquals(movie3, movies.get(1));
        assertEquals(2, myWatchedList.getNumWatchedMovies());
    }

    @Test
    void testRemoveMovieMultipleTimesFromList() {
        myWatchedList.addWatchedMovie(movie1);
        myWatchedList.addWatchedMovie(movie2);
        myWatchedList.addWatchedMovie(movie3);
        myWatchedList.removeMovieFromList("Interstellar");
        myWatchedList.removeMovieFromList("The Godfather");


        List<Movie> movies = myWatchedList.getWatchedMovies();

        assertEquals(1, movies.size());
        assertFalse(movies.contains(movie1));
        assertFalse(movies.contains(movie2));
        assertTrue(movies.contains(movie3));
        assertEquals(movie3, movies.get(0));
        assertEquals(1, myWatchedList.getNumWatchedMovies());
    }

    @Test
    void testGetMovieReturnNull() {
        myWatchedList.addWatchedMovie(movie1);
        myWatchedList.addWatchedMovie(movie2);
        myWatchedList.addWatchedMovie(movie3);

        assertEquals(null, myWatchedList.getMovie("Harry Potter"));
    }

    @Test
    void testGetMovie() {
        myWatchedList.addWatchedMovie(movie1);
        myWatchedList.addWatchedMovie(movie2);
        myWatchedList.addWatchedMovie(movie3);

        assertEquals(movie1, myWatchedList.getMovie("Interstellar"));
    }
}
