package persistence;

import model.Movie;
import model.Watchlist;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Watchlist wl = new Watchlist();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyWatchlist() {
        try{
            Watchlist wl = new Watchlist();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWatchlist.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWatchlist.json");
            wl = reader.read();
            assertEquals(0, wl.numMoviesRemaining());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWatchlist() {
        try {
            Watchlist wl = new Watchlist();
            wl.addMovie(new Movie("The Matrix", 1999));
            wl.addMovie(new Movie("Spirited Away", 2001));
            wl.addMovie(new Movie("Up", 2009));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWatchlist.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWatchlist.json");
            wl = reader.read();
            List<Movie> movies = wl.getMovies();
            assertEquals(3, movies.size());
            checkMovie("The Matrix", 1999, movies.get(0));
            checkMovie("Spirited Away", 2001, movies.get(1));
            checkMovie("Up", 2009, movies.get(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
