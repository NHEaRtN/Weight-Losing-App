package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a day with a list of exercises and a list of food intake
public class Day implements Writable {

   // private int dayNumber;
    private List<Exercise> dailyExerciseList;
    private List<Food> dailyFoodList;
    //private boolean isEntered;

    private static int baseCalorie = 2000;


    // EFFECTS: constructs a day with a new list of exercises and list of calories
    public Day() {
        dailyExerciseList = new ArrayList<>();
        dailyFoodList = new ArrayList<>();

        //this.dayNumber = dayNumber;
    }

    //getters

//    public int getDayNumber() {
//        return dayNumber;
//    }

    public List<Exercise> getExerciseList() {
        return this.dailyExerciseList;
    }

//    public void addExercise(String name, int caloriesBurned) {
//        Exercise e = new Exercise(name,caloriesBurned);
//        dailyExerciseList.add(e);
//    }


//    public void inputExercise(String name, int caloriesBurned) {
//        Exercise e = new Exercise(name, caloriesBurned);
//        dailyExerciseList.add(e);
//    }

//    public void inputFood(String name, int caloriesInput) {
//        Food f = new Food(name, caloriesInput);
//        dailyFoodList.add(f);
//    }

    public void addExercise(Exercise e) {
        EventLog.getInstance().logEvent(new Event("Added New Exercise : " + e.getExerciseName() + " ,"
                + e.getCaloriesBurned() + " Calories"));
        this.dailyExerciseList.add(e);
    }

    public void addFood(Food f) {
        EventLog.getInstance().logEvent(new Event("Added New Food : " + f.getFoodName() + " ,"
                + f.getFoodCalorie() + " Calories"));
        this.dailyFoodList.add(f);
    }

    public List<Food> getDailyFoodList() {
        return this.dailyFoodList;
    }

    public Food getFood(int index) {
        return this.dailyFoodList.get(index);
    }

    public Exercise getExercise(int index) {
        return this.dailyExerciseList.get(index);
    }

//    public void setDailyExerciseList(List<Exercise> loe) {
//        this.dailyExerciseList = loe;
//    }
//
//    public void setDailyFoodList(List<Food> lof) {
//        this.dailyFoodList = lof;
//    }





    //NEW

//    public List<String> getDailyFoodNames() {
//        List<String> foodNamesToday = new ArrayList<>();
//        for (Food f: dailyFoodList) {
//            foodNamesToday.add(f.getFoodName());
//        }
//        return foodNamesToday;
//    }
//
//    public List<String> getDailyExerciseNames() {
//        List<String> exerciseNamesToday = new ArrayList<>();
//        for (Exercise e: dailyExerciseList) {
//            exerciseNamesToday.add(e.getExerciseName());
//        }
//        return exerciseNamesToday;
//    }



    // EFFECTS: gets the total amount of calories output from exercises this day
    public int getDayOutput() {
        int outputCount = 0;
        for (Exercise e: dailyExerciseList) {
            outputCount += e.getCaloriesBurned();
        }
        return outputCount;
    }

    // EFFECTS: gets the total amount of calories input from food this day
    public int getDayInput() {
        int inputCount = 0;
        for (Food f: dailyFoodList) {
            inputCount += f.getFoodCalorie();
        }
        return inputCount;
    }

    // EFFECTS: gets the calorie deficit from this day taking into account all the input and output
    public int getDayCalorieDeficit()   {
        return baseCalorie + getDayOutput() - getDayInput();
    }



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("dayNumber",dayNumber);
        json.put("ListOfFood", foodsToJson());
        json.put("ListOfExercise",exercisesToJason());
        return json;
    }

    private JSONArray exercisesToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e: dailyExerciseList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f: dailyFoodList) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }

}

