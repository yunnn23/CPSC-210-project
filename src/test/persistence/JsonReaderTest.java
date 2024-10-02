package persistence;

import model.Movie;
import model.Watchlist;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Watchlist wl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyWatchlist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWatchlist.json");
        try {
            Watchlist wl = reader.read();
            List<Movie> movies = wl.getMovies();
            assertEquals(0, movies.size());
            assertEquals(0, wl.numMoviesRemaining());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWatchlist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWatchlist.json");
        try {
            Watchlist wl = reader.read();
            List<Movie> movies = wl.getMovies();
            assertEquals(3, movies.size());
            checkMovie("The Matrix", 1999, movies.get(0));
            checkMovie("Spirited Away", 2001, movies.get(1));
            checkMovie("Up", 2009, movies.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
