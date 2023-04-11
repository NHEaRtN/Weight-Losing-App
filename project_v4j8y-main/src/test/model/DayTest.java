package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTest {
    private Food f1;
    private Food f2;
    private Food f3;
    private Food f4;
    private Food f5;

    private Exercise e1;
    private Exercise e2;
    private Exercise e3;

    private Day testDay1;
    private Day testDay2;
    private Day testDay3;

    @BeforeEach

    void runBefore() {
        testDay1 = new Day();
        testDay2 = new Day();
        testDay3 = new Day();
        f1 = new Food ("Fried Chicken", 1000);
        f2 = new Food ("Caesar Salad", 350);
        f3 = new Food ("Milk&Cereal", 250);
        f4 = new Food ("Bread&PeanutButter", 500);
        f5 = new Food ("Chips", 150);
        e1 = new Exercise("Running 4km", 300);
        e2 = new Exercise("Basketball for 5 Hours", 500);
        e3 = new Exercise("Swimming",900);

        testDay1.addFood(f1);
        testDay1.addFood(f2);
        testDay1.addExercise(e1);

        testDay2.addFood(f3);
        testDay2.addFood(f4);
        testDay2.addFood(f5);
        testDay2.addExercise(e1);
        testDay2.addExercise(e2);
    }

    @Test
    void testConstructor() {
        assertEquals(f1.getFoodCalorie(),1000);
        assertEquals(f3.getFoodName(),"Milk&Cereal");
        assertEquals(e1.getCaloriesBurned(),300);
        assertEquals(e3.getExerciseName(),"Swimming");
        assertEquals(testDay1.getDailyFoodList().size(),2);
        assertEquals(testDay1.getExerciseList().size(),1);
    }
    @Test
    void testDailyCalDeficit() {
        assertEquals(testDay3.getDayCalorieDeficit(),2000);
        assertEquals(testDay3.getDayOutput(),0);
        assertEquals(testDay3.getDayInput(),0);
        testDay3.addExercise(e1);
        assertEquals(testDay3.getDayOutput(),e1.getCaloriesBurned());
        testDay3.addFood(f2);
        assertEquals(testDay3.getDayInput(),350);
        assertEquals(testDay2.getDayInput(),900);
        assertEquals(testDay2.getDayOutput(),800);
    }

    @Test
    void testGetters() {
        assertEquals(2,testDay1.getDailyFoodList().size());
        assertEquals(3,testDay2.getDailyFoodList().size());
        testDay2.addFood(f1);
        assertEquals(4,testDay2.getDailyFoodList().size());
        assertEquals(2,testDay2.getExerciseList().size());
    }
}
