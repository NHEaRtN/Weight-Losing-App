package persistence;

import model.Day;
import model.Exercise;
import model.ListOfDays;
import model.Food;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfDays from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfDays read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfDay(jsonObject);
    }

    // EFFECTS: reads source file and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses listOfDays from JSON object and returns it
    private ListOfDays parseListOfDay(JSONObject jsonObject) {
       // int lodCount = jsonObject.getInt("lodCount");
        ListOfDays lod = new ListOfDays();
        addListOfDays(lod, jsonObject);
        return lod;
    }

    // MODIFIES: lod
    // EFFECTS: parses Days from JSON object and adds them to ListOfDays
    private void addListOfDays(ListOfDays lod, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List Of Days");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addDay(lod, nextDay);
        }
    }

    // MODIFIES: lod
    // EFFECTS: parses Day its Exercises and Foods from JSON object and adds it to ListOfDays
    private void addDay(ListOfDays lod, JSONObject jsonObject) {
//        int dayNum = jsonObject.getInt("dayNumber");
        JSONArray jsonFoodArray = jsonObject.getJSONArray("ListOfFood");
        JSONArray jsonExerciseArray = jsonObject.getJSONArray("ListOfExercise");
        Day day = new Day();

        for (Object json: jsonFoodArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(day,nextFood);
        }

        for (Object json: jsonExerciseArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(day,nextExercise);
        }
        lod.addDay(day);
    }

    // MODIFIES: Day
    // EFFECTS: parses Exercise from JSON object and adds it to Day
    private void addExercise(Day day, JSONObject jsonObject) {
        String exerciseName = jsonObject.getString("exercise");
        int exerciseCalories = jsonObject.getInt("calorie");
        Exercise e = new Exercise(exerciseName,exerciseCalories);
        day.addExercise(e);
    }

    // MODIFIES: Day
    // EFFECTS: parses Foods from JSON object and adds it to Day
    private void addFood(Day day, JSONObject jsonObject) {
        String foodName = jsonObject.getString("foodName");
        int foodCalories = jsonObject.getInt("foodCalories");
        Food f = new Food(foodName,foodCalories);
        day.addFood(f);
    }
}
