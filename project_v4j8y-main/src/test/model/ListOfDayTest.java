package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfDayTest {
    private ListOfDays lod1;
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
        lod1 = new ListOfDays();

        testDay1 = new Day();
        testDay2 = new Day();
        testDay3 = new Day();
        lod1.addDay(testDay1);
        lod1.addDay(testDay2);
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
    void testGetters() {
        assertEquals(2,lod1.getSize());
        lod1.addDay(testDay3);
        assertEquals(3,lod1.getSize());

        assertEquals(testDay1,lod1.getDay(0));
    }
    @Test
    void testTotalCalorieDeficit() {
        assertEquals(2850,lod1.getTotalCalorieDeficit());
    }
}
