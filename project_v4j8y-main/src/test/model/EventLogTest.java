package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private Day day1;
    private ListOfDays lod;
    private Food f1;
    private Exercise e1;
    EventLog el = EventLog.getInstance();

    @BeforeEach
    void runBefore() {
        el.clear();
        day1 = new Day();
        lod = new ListOfDays();
        f1 = new Food("Chicken",1000);
        e1 = new Exercise("Swimming",300);
    }

    @Test
    void testEventLog() {
        int i = 0;
        for(Event e: el) {
            i++;
        }
        assertTrue(el.iterator().hasNext()); // Because there is an event of "Event log cleared."
        Event e = (Event) el.iterator().next();
        assertEquals("Event log cleared.",e.getDescription());

        day1.addFood(f1);
        List<Event> ev = new ArrayList<>();
        for(Event event: el) {
            ev.add(event);
        }
        assertEquals(2,ev.size());
        assertEquals("Added New Food : Chicken ,1000 Calories",ev.get(1).getDescription());

        day1.addExercise(e1);
        List<Event> ev2 = new ArrayList<>();
        for(Event event: el) {
            ev2.add(event);
        }
        assertEquals(3,ev2.size());
        assertEquals("Added New Exercise : Swimming ,300 Calories",ev2.get(2).getDescription());

        lod.addDay(day1);
        List<Event> ev3 = new ArrayList<>();
        for(Event event: el) {
            ev3.add(event);
        }
        assertEquals(4,ev3.size());
        assertEquals("Added a New Day: Day0",ev3.get(3).getDescription());



    }

}
