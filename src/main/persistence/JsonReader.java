package persistence;

import model.Movie;
import model.Watchlist;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads watchlist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads watchList from file and returns it
    //          throws IOException if an error occurs when reading data from file
    public Watchlist read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonobject = new JSONObject(jsonData);
        return parseWatchlist(jsonobject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses watchlist from JSON object and returns it
    private Watchlist parseWatchlist(JSONObject jsonObject) {
        Watchlist wl = new Watchlist();
        addMovies(wl, jsonObject);
        return wl;
    }

    // MODIFIES: wl
    // EFFECTS: parses movies from JSON object and adds them to Watchlist
    private void addMovies(Watchlist wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(wl, nextMovie);
        }
    }

    // MODIFIES: wl
    // EFFECTS: parses movie from JSON object and adds it to watchlist
    private void addMovie(Watchlist wl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int year = jsonObject.getInt("year");
        Movie movie = new Movie(title, year);
        wl.addMovie(movie);
    }
}
