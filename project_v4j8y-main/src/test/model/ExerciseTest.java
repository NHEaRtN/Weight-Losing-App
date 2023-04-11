package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {

    private Exercise e1;
    private Exercise e2;
    private Exercise e3;


    @BeforeEach
    void runBefore() {
        e1 = new Exercise("Running 4km", 300);
        e2 = new Exercise("Basketball for 5 Hours", 500);
        e3 = new Exercise("Swimming",900);

    }


    @Test

    void testConstructors() {
        assertEquals("Running 4km", e1.getExerciseName());
        assertEquals("Basketball for 5 Hours", e2.getExerciseName());
        assertEquals(900, e3.getCaloriesBurned());
    }

    @Test
    void testSetters() {
        assertEquals("Running 4km", e1.getExerciseName());
        e1.setExerciseName("Hiking");
        assertEquals("Hiking",e1.getExerciseName());
        assertEquals(500, e2.getCaloriesBurned());
        e2.setCaloriesBurned(100);
        assertEquals(100, e2.getCaloriesBurned());
    }
}
