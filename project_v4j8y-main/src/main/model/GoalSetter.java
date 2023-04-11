package model;

// Allows users to input their current and goal weight, set days they plan to spend to reach that goal, and then
// users would get the calorie deficit per day needed for that plan to happen


public class GoalSetter {
    private static int caloriePerKg = 7000;
    private int currentWeightKg;
    private int goalWeightKg;
    private int plannedDaysReachGoal;
//    private int goalCalories;

    // EFFECTS: constructs a GoalSetter with int goal weight and current weight, as well as the days they plan to reach
    //          the goal

    public GoalSetter(int currentWht, int goalWgt, int days) {
        this.currentWeightKg = currentWht;
        this.goalWeightKg = goalWgt;
        this.plannedDaysReachGoal = days;
    }

    //getters
//    public int getCurrentWeightKg() {
//        return this.currentWeightKg;
//    }
//
//    public int getGoalWeightKg() {
//        return this.goalWeightKg;
//    }

    public int getPlannedDaysReachGoal() {
        return this.plannedDaysReachGoal;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeightKg = currentWeight;
    }

    public void setGoalWeight(int goalWeight) {
        this.goalWeightKg = goalWeight;
    }

    public void setPlannedDaysReachGoal(int days) {
        this.plannedDaysReachGoal = days;
    }

    // EFFECTS: returns the total kg they would lose according to their plan
    public int kgPlanningToLose() {
        return this.currentWeightKg - this.goalWeightKg;
    }

//    // REQUIRES: currentWeight > goalWeight
//    public int setGoalCalorie() {
//        return (this.currentWeightKg - this.goalWeightKg) * caloriePerKg;
//    }

    // EFFECTS: returns the calorie deficit per day needed to reach users' goal based on the plan that they set up

    public int getDeficitPerDay() {
        return ((currentWeightKg - goalWeightKg) * caloriePerKg) / plannedDaysReachGoal;
//        if (caloriePerDay > 1000) {
//            return 0;
//        } else {

    }
}
