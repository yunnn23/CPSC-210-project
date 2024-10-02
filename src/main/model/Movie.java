package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a movie having its title and released year
public class Movie implements Writable {

    private String title;
    private int year;
    private int score;

    // REQUIRES: movieTitle has non-zero length, releaseYear > 1800
    // EFFECTS: The title of the movie is set to movieTitle.
    //          The release year of the movie is set to releaseYear.
    public Movie(String movieTitle, int releaseYear) {
        this.title = movieTitle;
        this.year = releaseYear;
    }

    // REQUIRES: 1 <= score <= 5
    // MODIFIES: this
    // EFFECTS: Rate the movie with a score from 1 to 5
    public void rating(int points) {
        this.score = points;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getRating() {
        return score;
    }

    @Override
    // EFFECTS: returns movie in this watchlist as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("year", year);
        return json;
    }

    @Override
    // EFFECTS: returns string of movie title and year
    public String toString() {
        return title + " " + String.valueOf(year);
    }
}
