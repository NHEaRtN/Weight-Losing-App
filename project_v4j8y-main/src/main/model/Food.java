package model;


import org.json.JSONObject;
import persistence.Writable;

// represents a food with a name and calories
public class Food implements Writable {

    private String foodName;
    private int foodCalorie;

    // EFFECTS: constructs a Food with string name and int calories that the food contains
    public Food(String name, int calorie) {
        this.foodName = name;
        this.foodCalorie = calorie;
    }

    // getters
    public String getFoodName() {
        return foodName;
    }

    public int getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodName(String name) {
        this.foodName = name;
    }

    public void setFoodCalorie(int calorie) {
        this.foodCalorie = calorie;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foodName", foodName);
        json.put("foodCalories", foodCalorie);
        return json;
    }
}
