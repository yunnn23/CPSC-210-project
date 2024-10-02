package ui;

import model.Event;
import model.EventLog;
import model.Movie;
import model.Watchlist;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Represents application's main window frame
public class MovieListGUI extends JPanel implements ListSelectionListener {
    private static final String JSON_STORE = "./data/watchlist.json";
    private JList list;
    private static DefaultListModel listModel;
    private static Watchlist movieList;
    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String loadString = "load";
    private JButton removeButton;
    private JButton loadButton;
    private JTextField movieTitleAndYear;
    private ImageIcon movieImage;

    // Helper to set up button panel and visualize a movie list in the window
    // Create the list and put it in a scroll pane
    public MovieListGUI() {

        movieList = new Watchlist();
        movieList.addMovie(new Movie("Interstellar", 2014));
        movieList.addMovie(new Movie("The Godfather", 1972));
        movieList.addMovie(new Movie("Inception", 2010));

        listModel = new DefaultListModel<Movie>();
        for (Movie m : movieList.getMovies()) {
            listModel.addElement(m);
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);

        movieTitleAndYear = new JTextField(10);

        add(listScrollPane, BorderLayout.CENTER);
        add(getButtonPane(), BorderLayout.PAGE_END);
        loadImage();
    }

    // Helper to add remove, add, load buttons
    public JPanel getButtonPane() {
        JButton addButton = new JButton(addString);
        addButton.addActionListener(new AddListener());

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());

        // Create a panel that uses BoxLayout
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(movieTitleAndYear);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(loadButton);

        return buttonPane;
    }

    // Represents action to be performed when user wants to add a new movie
    class AddListener implements ActionListener {

        @Override
        // EFFECTS: Adds a movie to the watchlist and list model
        public void actionPerformed(ActionEvent e) {
            // String titleAndYear = movieTitleAndYear.getText();
            // String[] arr = titleAndYear.split(" ");//requires movietitle year
            Pattern p = Pattern.compile("([\\w\\s]+) (\\d{4})");
            Matcher m = p.matcher(movieTitleAndYear.getText().trim());
            m.find();
            String title = m.group(1);
            int year = Integer.parseInt(m.group(2));

            Movie movie = new Movie(title, year);
            movieList.addMovie(movie);
            listModel.addElement(movie);
            movieTitleAndYear.setText("");

            String sep = System.getProperty("file.separator");
            ImageIcon movieIcon = new ImageIcon(System.getProperty("user.dir") + sep
                    + "images" + sep + "movie_icon.png");
            JOptionPane.showConfirmDialog(null, "Successfully added!",
                    "Confirmation message", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, movieIcon);

            if (title.equals("") || alreadyInList(title)) {
                Toolkit.getDefaultToolkit().beep();
                movieTitleAndYear.requestFocusInWindow();
                movieTitleAndYear.selectAll();
                return;
            }
        }
    }

    // Checking if the given title is already in the list
    protected boolean alreadyInList(String title) {
        return listModel.contains(title);
    }

    // Represents action to be performed when users want to remove a movie
    class RemoveListener implements ActionListener {

        // EFFECTS: Removes a movie which is indexed from the watchlist and list model
        public void actionPerformed(ActionEvent e) {
            // can be called only if there's a valid selection
            int index = list.getSelectedIndex();
            movieList.removeMovie(index);
            listModel.remove(index);

            int size = movieList.numMoviesRemaining();

            if (size == 0) {
                removeButton.setEnabled(false);

            } else {
                if (index == movieList.numMoviesRemaining()) {
                    // removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // Represents action to be performed when user wants to load the data
    class LoadListener implements ActionListener {

        @Override
        // EFFECTS: Loads the stored data which is previously saved in the file
        public void actionPerformed(ActionEvent e) {
            JsonReader jsonReader = new JsonReader(JSON_STORE);
            try {
                Watchlist tempMovieList = jsonReader.read();
                //listModel.clear();
                for (Movie m : tempMovieList.getMovies()) {
                    movieList.addMovie(m);
                    listModel.addElement(m);
                }
                //System.out.println("Saved watchlist to " + JSON_STORE);
            } catch (FileNotFoundException ex) {
                //System.out.println("Unable to write to file: " + JSON_STORE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // This is required by ListSelectionListener
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                // No selection, disable remove button
                removeButton.setEnabled(false);

            } else {
                // selection, enable the remove button
                removeButton.setEnabled(true);
            }
        }
    }

    // Displaying the background image
    private void loadImage() {
        String sep = System.getProperty("file.separator");

        movieImage = new ImageIcon(System.getProperty("user.dir") + sep + "images" + sep + "movie.png");

        JLabel imageAsLabel = new JLabel(movieImage);
        add(imageAsLabel);
    }

    // EFFECTS: Create the GUI and set up the window
    private static void createdAndShowGUI() throws FileNotFoundException {
        JFrame frame = new JFrame("MovieList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new MyWindowAdapter(frame));
        frame.setContentPane(new MovieListGUI());

        // Display the window
        frame.pack();
        frame.setVisible(true);
    }

    // Represents action to be taken when users want to save the data before closing the window
    public static class MyWindowAdapter extends WindowAdapter {
        JFrame frame;

        // EFFECTS: To create lister objects
        public MyWindowAdapter(JFrame frame) {
            this.frame = frame;
        }

        @Override
        // EFFECTS: Happen when a window is being closed
        public void windowClosing(WindowEvent e) {
            int answer = JOptionPane.showConfirmDialog(frame, "Do you want to save it?",
                    "Quit", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

                try {
                    jsonWriter.open();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                jsonWriter.write(movieList);
                jsonWriter.close();
                printEventLog();

            } else {
                printEventLog();
            }
        }
    }

    // printing all the events
    public static void printEventLog() {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

    // Creating and showing this application's GUI
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createdAndShowGUI();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
