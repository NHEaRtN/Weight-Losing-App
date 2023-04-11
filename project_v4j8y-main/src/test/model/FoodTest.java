package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {


    private Food f1;
    private Food f2;
    private Food f3;


    @BeforeEach
    void runBefore() {
        f1 = new Food ("Fried Chicken", 1000);
        f2 = new Food ("Milk&Cereal", 250);
        f3 = new Food ("Bread&PeanutButter", 500);
    }




    @Test

    void testConstructors() {
        assertEquals("Fried Chicken", f1.getFoodName());
        assertEquals("Bread&PeanutButter", f3.getFoodName());
        assertEquals(500, f3.getFoodCalorie());
    }

    @Test
    void testSetters() {
        assertEquals("Fried Chicken", f1.getFoodName());
        f1.setFoodName("Beef");
        assertEquals("Beef",f1.getFoodName());
        assertEquals(250, f2.getFoodCalorie());
        f2.setFoodCalorie(600);
        assertEquals(600, f2.getFoodCalorie());
    }









}
