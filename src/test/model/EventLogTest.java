package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private Event e1;
    private Event e2;
    private Date dateLogged;
    private Watchlist wl;
    private Movie m;

    @BeforeEach
    public void loadEvents() {
        e1 = new Event("The movie was added");
        e2 = new Event("The movie was removed");
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);
        dateLogged = Calendar.getInstance().getTime();
        m = new Movie("abc", 1994);
    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));

        assertEquals("The movie was added", e1.getDescription());
        assertEquals("The movie was removed", e2.getDescription());
        assertEquals(dateLogged.toString() + "\n" + e1.getDescription(), e1.toString());
        assertEquals(dateLogged.toString() + "\n" + e2.getDescription(), e2.toString());
    }

    @Test
    public void testAddMovie() {
        wl = new Watchlist();
        wl.addMovie(m);
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);

        assertEquals("The movie was added", e1.getDescription());
    }

    @Test
    public void testRemoveMovie() {
        wl = new Watchlist();
        wl.addMovie(m);
        wl.removeMovie(m);
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);

        assertEquals("The movie was added", e1.getDescription());
        assertEquals("The movie was removed", e2.getDescription());
    }

    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> itr = el.iterator();
        assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
