package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WatchlistTest {
    private Watchlist myWatchlist;
    private WatchedList watchedMovies;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @BeforeEach
    void setup() {
        myWatchlist = new Watchlist();
        watchedMovies = new WatchedList();
        movie1 = new Movie("Interstellar", 2014);
        movie2 = new Movie("The Godfather", 1972);
        movie3 = new Movie("Inception", 2010);
    }

    @Test
    void testConstructor() {
        assertEquals(0, myWatchlist.getMovies().size());
        assertEquals(0, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testAddMovie() {
        myWatchlist.addMovie(movie1);

        assertTrue(myWatchlist.getMovies().contains(movie1));
        assertEquals(movie1, myWatchlist.getMovies().get(0));
        assertEquals(1, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testAddMoviesMultipleTimes() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);

        List<Movie> movieList = myWatchlist.getMovies();

        assertTrue(movieList.contains(movie1));
        assertTrue(movieList.contains(movie2));
        assertTrue(movieList.contains(movie3));

        assertEquals(movie1, movieList.get(0));
        assertEquals(movie2, movieList.get(1));
        assertEquals(movie3, movieList.get(2));


        assertEquals(3, myWatchlist.getMovies().size());
        assertEquals(3, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testRemoveMovie() {
        myWatchlist.addMovie(movie1);
        myWatchlist.removeMovie(movie1);

        List<Movie> movieList = myWatchlist.getMovies();

        assertFalse(movieList.contains(movie1));
        assertEquals(0, movieList.size());
        assertEquals(0, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testRemoveMovieMultipleTimes() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);
        myWatchlist.removeMovie(movie1);
        myWatchlist.removeMovie(movie2);

        List<Movie> movieList = myWatchlist.getMovies();

        assertFalse(movieList.contains(movie1));
        assertFalse(movieList.contains(movie2));
        assertTrue(movieList.contains(movie3));

        assertEquals(movie3, movieList.get(0));
        assertEquals(1, movieList.size());
        assertEquals(1, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testRemoveMovieFromListToFail() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);
        myWatchlist.removeMovieFromList("Harry Potter");

        List<Movie> movies = myWatchlist.getMovies();

        assertEquals(3, movies.size());
        assertTrue(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
        assertTrue(movies.contains(movie3));
        assertEquals(movie1, movies.get(0));
        assertEquals(movie2, movies.get(1));
        assertEquals(movie3, movies.get(2));
        assertEquals(3, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testRemoveMovieFromList() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);
        myWatchlist.removeMovieFromList("Interstellar");

        List<Movie> movies = myWatchlist.getMovies();

        assertEquals(2, movies.size());
        assertFalse(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
        assertTrue(movies.contains(movie3));
        assertEquals(movie2, movies.get(0));
        assertEquals(movie3, movies.get(1));
        assertEquals(2, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testRemoveMovieMultipleTimeFromList() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);
        myWatchlist.removeMovieFromList("Interstellar");
        myWatchlist.removeMovieFromList("Inception");

        List<Movie> movies = myWatchlist.getMovies();

        assertEquals(1, movies.size());
        assertFalse(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
        assertFalse(movies.contains(movie3));
        assertEquals(movie2, movies.get(0));
        assertEquals(1, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testRemoveMovieWithGivenIndex() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);
        myWatchlist.removeMovie(0);

        List<Movie> movies = myWatchlist.getMovies();

        assertEquals(2, movies.size());
        assertFalse(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
        assertTrue(movies.contains(movie3));
        assertEquals(movie2, movies.get(0));
        assertEquals(2, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testRemoveMovieMultipleTimesWithGivenIndex() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);
        myWatchlist.removeMovie(0);

        List<Movie> movies = myWatchlist.getMovies();

        assertEquals(2, movies.size());
        assertFalse(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
        assertTrue(movies.contains(movie3));
        assertEquals(movie2, movies.get(0));
        assertEquals(2, myWatchlist.numMoviesRemaining());

        myWatchlist.removeMovie(0);

        assertEquals(1, movies.size());
        assertFalse(movies.contains(movie1));
        assertFalse(movies.contains(movie2));
        assertTrue(movies.contains(movie3));
        assertEquals(movie3, movies.get(0));
        assertEquals(1, myWatchlist.numMoviesRemaining());
    }

    @Test
    void testWatchMovieToFail() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);

        myWatchlist.watchMovie("HarryPotter");

        List<Movie> movieList = myWatchlist.getMovies();

        assertEquals(movie1, myWatchlist.getMovies().get(0));
        assertEquals(movie2, myWatchlist.getMovies().get(1));
        assertEquals(movie3, myWatchlist.getMovies().get(2));
        assertTrue(movieList.contains(movie1));
        assertTrue(movieList.contains(movie2));
        assertTrue(movieList.contains(movie3));
        assertEquals(3, movieList.size());
        assertEquals(3, myWatchlist.numMoviesRemaining());
    }


    @Test
    void testWatchMovie() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);

        myWatchlist.watchMovie("Interstellar");

        List<Movie> movieList = myWatchlist.getMovies();

        assertEquals(movie2, myWatchlist.getMovies().get(0));
        assertEquals(movie3, myWatchlist.getMovies().get(1));
        assertFalse(movieList.contains(movie1));
        assertTrue(movieList.contains(movie2));
        assertTrue(movieList.contains(movie3));
        assertEquals(2, movieList.size());
        assertEquals(2, myWatchlist.numMoviesRemaining());

        watchedMovies.addWatchedMovie(movie1);

        List<Movie> watchedMovieList = watchedMovies.getWatchedMovies();
        assertTrue(watchedMovieList.contains(movie1));
        assertEquals(movie1, watchedMovieList.get(0));
        assertEquals(1, watchedMovieList.size());
        assertEquals(1, watchedMovies.getNumWatchedMovies());
    }

    @Test
    void testWatchMovieMultipleTimes() {
        myWatchlist.addMovie(movie1);
        myWatchlist.addMovie(movie2);
        myWatchlist.addMovie(movie3);

        myWatchlist.watchMovie("Interstellar");
        myWatchlist.watchMovie("Inception");

        List<Movie> movieList = myWatchlist.getMovies();

        assertEquals(movie2, myWatchlist.getMovies().get(0));
        assertFalse(movieList.contains(movie1));
        assertTrue(movieList.contains(movie2));
        assertFalse(movieList.contains(movie3));
        assertEquals(1, movieList.size());
        assertEquals(1, myWatchlist.numMoviesRemaining());

        watchedMovies.addWatchedMovie(movie1);
        watchedMovies.addWatchedMovie(movie3);

        List<Movie> watchedMovieList = watchedMovies.getWatchedMovies();

        assertTrue(watchedMovieList.contains(movie1));
        assertTrue(watchedMovieList.contains(movie3));
        assertEquals(movie1, watchedMovieList.get(0));
        assertEquals(movie3, watchedMovieList.get(1));
        assertEquals(2, watchedMovieList.size());
        assertEquals(2, watchedMovies.getNumWatchedMovies());
    }
}
