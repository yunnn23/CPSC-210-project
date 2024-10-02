package ui;

import model.Movie;
import model.WatchedList;
import model.Watchlist;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the watchlist application
public class MovieApp {
    private static final String JSON_STORE = "./data/watchlist.json";
    private Watchlist watchlist;
    private WatchedList watchedList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs watchlist and runs application
    public MovieApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        watchlist = new Watchlist();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            input.nextLine();//clear buffer

            if (command.equals("8")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            viewWatchlist();
        } else if (command.equals("2")) {
            viewWatchedList();
        } else if (command.equals("3")) {
            doRating();
        } else if (command.equals("4")) {
            doAdd();
        } else if (command.equals("5")) {
            doRemove();
        } else if (command.equals("6")) {
            saveWatchlist();
        } else if (command.equals("7")) {
            loadWatchlist();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: initializes watchlist
    private void init() {
        this.watchlist = new Watchlist();
        Movie movie1 = new Movie("About Time", 2013);
        Movie movie2 = new Movie("The Godfather", 1972);
        Movie movie3 = new Movie("Inception", 2010);
        watchlist.addMovie(movie1);
        watchlist.addMovie(movie2);
        watchlist.addMovie(movie3);

        //this.myWatchedList = new WatchedList();
        this.watchedList = this.watchlist.getWatchedList();
        Movie movie4 = new Movie("In Time", 2011);
        watchedList.addWatchedMovie(movie4);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options for users
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\n1 -> my list");
        System.out.println("\n2 -> my watched list");
        System.out.println("\n3 -> rating movie");
        System.out.println("\n4 -> add");
        System.out.println("\n5 -> remove");
        System.out.println("\n6 -> save watch list to file");
        System.out.println("\n7 -> load watch list from file");
        System.out.println("\n8 -> quit");
    }

    // EFFECTS: view all the movies in watchlist to the console
    private void viewWatchlist() {

        for (Movie m : watchlist.getMovies()) {
            System.out.println(m.getTitle() + " (" + m.getYear() + ") Rating:" + m.getRating());
            //Interstellar (2019) Rating: 5.0
        }
    }

    // EFFECTS: view all the movies in watched list to the console
    private void viewWatchedList() {

        for (Movie m : watchedList.getWatchedMovies()) {
            System.out.println(m.getTitle() + " (" + m.getYear() + ") Rating:" + m.getRating());
            //Interstellar (2019) Rating: 5.0
        }
    }

    // EFFECTS: give points for a movie
    private void doRating() {
        System.out.println("What movie do you want to rate?");
        String name = input.nextLine();
        Movie m = watchedList.getMovie(name);
        if (m != null) {
            System.out.println("Enter points to rate 1-5");
            int points = input.nextInt();
            input.nextLine();//clear buffer

            if (points < 1) {
                System.out.println("Cannot rate less than 1 point");
            } else if (points > 5) {
                System.out.println("Cannot rate more than 5 points");
            } else {
                m.rating(points);
            }

            System.out.println("Points: " + m.getRating());
        }
    }

    // EFFECTS: prompt user for title and release year of movie and adds to watchlist
    private void doAdd() {
        System.out.println("Please enter movie title");
        String name = input.nextLine();
        System.out.println("Please enter movie release year");
        int year = input.nextInt();
        input.nextLine();//clear buffer
        Movie m = new Movie(name, year);
        watchlist.addMovie(m);
    }

    // EFFECTS: prompt user for title of movie and removes from watchlist and watched list
    private void doRemove() {
        System.out.println("Please enter movie title");
        String name = input.nextLine();
        watchlist.removeMovieFromList(name);
        watchedList.removeMovieFromList(name);
    }

    // EFFECTS: saves the watchlist to file
    private void saveWatchlist() {
        try {
            jsonWriter.open();
            jsonWriter.write(watchlist);
            jsonWriter.close();
            System.out.println("Saved watchlist to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads watchlist from file
    private void loadWatchlist() {
        try {
            watchlist = jsonReader.read();
            System.out.println("Loaded watchlist from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}


