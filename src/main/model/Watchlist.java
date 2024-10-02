package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a movie list of movies
public class Watchlist implements Writable {
    private List<Movie> movies;
    private WatchedList watchedList;

    // EFFECTS: constructs a list of movies that one has not seen yet
    public Watchlist() {
        this.movies = new ArrayList<>();
        watchedList = new WatchedList();
    }

    // MODIFIES: this
    // EFFECTS: add the given movie to the list
    public void addMovie(Movie movie) {
        this.movies.add(movie);
        EventLog.getInstance().logEvent(new Event("The movie was added."));
    }

    // MODIFIES: this
    // EFFECTS: removes a movie from the watchlist
    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
        EventLog.getInstance().logEvent(new Event("The movie was removed."));
    }

    // MODIFIES: this
    // EFFECTS: remove a movie with the given index
    public void removeMovie(int index) {
        this.movies.remove(index);
        EventLog.getInstance().logEvent(new Event("The movie was removed."));
    }

    // MODIFIES: this
    // EFFECTS: removes a movie from the watchlist with given title
    public void removeMovieFromList(String name) {
        for (Movie m : this.movies) {
            if (m.getTitle().equals(name)) {
                this.movies.remove(m);
                break;
            }
        }
    }

    // REQUIRES: title = movieTitle, the number of movies > 0
    // MODIFIES: this
    // EFFECTS: If one watch a movie which is in the list, we can delete the movie from the list with given title.
    //          If one does not watch a movie, then we keep the movie in the list.
    public List<Movie> watchMovie(String title) {
        for (Movie m : this.movies) {
            if (title == m.getTitle()) {
                this.movies.remove(m);
                this.watchedList.addWatchedMovie(m);
                break;
            }
        }
        return movies;
    }

    // EFFECTS: returns the number of movies remaining
    public int numMoviesRemaining() {
        return this.movies.size();
    }

    // EFFECTS: returns a list of movies that has not been seen yet
    public List<Movie> getMovies() {
        return movies;
    }

    public WatchedList getWatchedList() {
        return watchedList;
    }

    @Override
    // EFFECTS: returns movie in this watchlist as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movies", moviesToJson());
        return json;
    }

    // EFFECTS: returns movies in this watchlist as a JSON array
    private JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : movies) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
