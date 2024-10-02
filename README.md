# Movie List Application

A movie List application. Users can make a watchlist of movies that they have not seen yet. 
If they watch a movie from the watchlist, the movie will be added to the watched movie list.
They also can rate the movie after they watched a movie.
It would be useful for users who are interested in movies and want to record movies they have seen so that
they can keep track of the movies watched. 
Also, they can make a watchlist of movies that they want to see in the future.
As I am a movie fan, I always thought it would be nice to have a tracking list of movies for one’s record.

# Users Stories
- As a user, I want to be able to rate a movie with points range from 1 to 5
- As a user, I want to be able to view a list of movies in my watch/watched movie list
- As a user, I want to be able to add a movie to my watchlist
- As a user, I want to be able to remove a movie from the watchlist
- As a user, I want to be able to save my movie list to file
- As a user, I want to be able to be able to load my movie list from file

# Instructions for Grader
- You can generate the first required event related to adding multiple movies to a movie list by 
  clicking the button labelled "add".
- You can generate the second required event related to removing multiple movies from a movie list 
  by clicking the button labelled "remove".
- You can locate my visual component by the background image of the application and the Confirmation message 
  when you successfully add a movie in the list.
- You can save the state of my application by clicking "yes" on the Quit message 
  when you try to quit the application.
- You can reload the state of my application by clicking the button labelled "load".

# Phase 4: Task 2
- Thu Dec 01 01:23:47 PST 2022
  The movie was added.
- Thu Dec 01 01:23:50 PST 2022
  The movie was added.
- Thu Dec 01 01:23:53 PST 2022
  The movie was removed.
- Thu Dec 01 01:23:57 PST 2022
  The movie was added.
- Thu Dec 01 01:24:01 PST 2022
  The movie was removed.
- Thu Dec 01 01:24:01 PST 2022
  The movie was removed.

# Phase 4: Task 3
If I had more time to work on the project, I would refactor my MovieListGUI class in the ui package.
There are 4 inner classes in the MovieListGui class, which seems to be a little messy and complicated.
I would take these 4 classes out from this class and create them as a single class in the ui package.
By refactoring the MovieListGui class, my design could be improved, making the GUI class be tidy and easy to understand.
In addition, I would create a method that helps make an ImageIcon, which can avoid duplicating some codes.

# Citation
- JsonSerializationDemo provided by CPSC 210
- AlarmSystem provided by CPSC 210
- https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
- https://www.pngwing.com/en/free-png-brpju
- https://www.freeiconspng.com/downloadimg/15154