package model;

import java.util.ArrayList;
import java.util.List;

// Represents a movie list of watched movies
public class WatchedList {
    private List<Movie> watchedMovies;

    // EFFECTS: constructs a list of movies that one has watched
    public WatchedList() {
        this.watchedMovies = new ArrayList<>();
    }

    // REQUIRES: movieTitle has non-zero length, releaseYear > 1800
    // MODIFIES: this
    // EFFECTS: adds a movie to the list of movies that one watched
    public void addWatchedMovie(Movie movie) {
        this.watchedMovies.add(movie);
    }

    // MODIFIES: this
    // EFFECTS: removes a movie from the watched list
    public void removeWatchedMovie(Movie movie) {
        this.watchedMovies.remove(movie);
    }

    // MODIFIES: this
    // EFFECTS: removes a movie from the watched list with given title
    public void removeMovieFromList(String name) {
        for (Movie m : this.watchedMovies) {
            if (m.getTitle().equals(name)) {
                this.watchedMovies.remove(m);
                break;
            }
        }
    }

    // EFFECTS: returns a list of movies that has not been seen yet
    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    // EFFECTS: returns the number of movies watched
    public int getNumWatchedMovies() {
        return watchedMovies.size();
    }

    // MODIFIES: this
    // EFFECTS: returns a movie with the given title, otherwise returns null
    public Movie getMovie(String name) {
        for (Movie m : this.watchedMovies) {
            if (m.getTitle().equals(name)) {
                return m;
            }
        }
        return null;
    }
}
