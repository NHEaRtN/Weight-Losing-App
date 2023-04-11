package persistence;

import model.Day;
import model.Exercise;
import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkDay(Day day, int expectedOutput, int expectedInput, int expectedDeficit) {
        assertEquals(expectedOutput,day.getDayOutput());
        assertEquals(expectedInput,day.getDayInput());
        assertEquals(expectedDeficit,day.getDayCalorieDeficit());
    }
    protected void checkExercise(String name, int caloriesBurned, Exercise e) {
        assertEquals(name, e.getExerciseName());
        assertEquals(caloriesBurned, e.getCaloriesBurned());
    }
    protected void checkFood(String name, int caloriesConsumed, Food f) {
        assertEquals(name,f.getFoodName());
        assertEquals(caloriesConsumed,f.getFoodCalorie());
    }
}
