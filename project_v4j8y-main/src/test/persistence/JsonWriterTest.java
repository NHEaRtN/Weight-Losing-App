package persistence;

import model.Day;
import model.Exercise;
import model.Food;
import model.ListOfDays;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfDays lod = new ListOfDays();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfDays lod = new ListOfDays();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfDays.json");
            writer.open();
            writer.write(lod);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            lod = reader.read();
//            assertEquals("My work room", wr.getName());
            assertEquals(0, lod.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfDays lod = new ListOfDays();
            lod.addNewDay();
            Day day1 = lod.getDay(0);
            Food food1 = new Food("chicken",1000);
            Exercise exercise1 = new Exercise("swimming",200);
            day1.addFood(food1);
            day1.addExercise(exercise1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListOfDays.json");
            writer.open();
            writer.write(lod);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListOfDays.json");
            lod = reader.read();
            List<Day> days = lod.getListOfDays();
            assertEquals(1, days.size());
            Day firstDay = days.get(0);

            List<Food> day1FoodList = firstDay.getDailyFoodList();
            assertEquals(1,day1FoodList.size());
            checkFood("chicken",1000,firstDay.getFood(0));
            checkExercise("swimming",200,firstDay.getExercise(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}