package persistence;

import model.*;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfDays lod = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfDays() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfDays.json");
        try {
            ListOfDays lod = reader.read();
//            assertEquals(0, wr.numThingies());
            assertEquals(0,lod.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfDays.json");
        try {
            ListOfDays listOfDays = reader.read();
           // assertEquals("My work room", wr.getName());
            List<Day> days = listOfDays.getListOfDays();
            assertEquals(7, days.size());
            Day day1 = days.get(0);
            checkDay(days.get(0),200,1000,1200);

            List<Food> day1FoodList = day1.getDailyFoodList();
            assertEquals(1,day1FoodList.size());
            checkFood("chicken",1000,day1.getFood(0));
            checkExercise("swimming",200,day1.getExercise(0));

            Day day2 = days.get(1);
            List<Food> day2FoodList = day2.getDailyFoodList();
            List<Exercise> day2ExerciseList = day2.getExerciseList();

            checkDay(day2,3100,3141,1959);
            assertEquals(2,day2FoodList.size());
            assertEquals(2,day2ExerciseList.size());
            checkExercise("basketball",3000,day2.getExercise(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}