package model;


import org.json.JSONObject;
import persistence.Writable;

// Represents an exercise with name and calories burned
public class Exercise implements Writable {

    private String exerciseName;
    private int caloriesBurned;

// EFFECTS: constructs an exercise with string name and int calories burned
    public Exercise(String name, int calorie) {
        this.exerciseName = name;
        this.caloriesBurned = calorie;
    }

    // getters
    public String getExerciseName() {
        return exerciseName;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }


    public void setExerciseName(String x) {
        this.exerciseName = x;
    }

    public void setCaloriesBurned(int calBurned) {
        this.caloriesBurned = calBurned;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("exercise", exerciseName);
        json.put("calorie", caloriesBurned);
        return json;
    }
}
