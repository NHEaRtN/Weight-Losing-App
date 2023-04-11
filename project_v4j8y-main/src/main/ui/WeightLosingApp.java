package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Weight losing application
public class WeightLosingApp implements Printer {
    private static final String JSON_STORE = "./data/listofdays.json";

    private ListOfDays listOfDays;

    private GoalSetter gs;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the WeightLoosing application
    public WeightLosingApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runWeightLoosing();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runWeightLoosing() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenuForMain();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        printEvents(EventLog.getInstance());
        System.out.println("\nThank you for using our app!!");
    }

    // MODIFIES: this
    // EFFECTS: initializes list of days and goal setter
    private void init() {
        listOfDays = new ListOfDays();
        gs = new GoalSetter(0, 0, 0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }



    // EFFECTS: displays MAIN menu of options to the user
    private void displayMenuForMain() {
        System.out.println("\nSelect from:");
        System.out.println("\tg -> Set A Goal");
        System.out.println("\tv -> View Past A Past Day's Activities");
        System.out.println("\tr -> Record Activities Done Today");
        System.out.println("\tx -> Remove The Last Day");
        System.out.println("\tl -> Load Past Days From File");
        System.out.println("\ts -> Save These Days To File");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for MAIN menu
    private void processCommand(String command) {
        if (command.equals("g")) {
            doGoalSetter();
        } else if (command.equals("v")) {
            doViewPastDays();
        } else if (command.equals("r")) {
            doRecordNewDay();
        } else if (command.equals("x")) {
            removeDay();
        } else if (command.equals("l")) {
            loadFiles();
        } else if (command.equals("s")) {
            saveFiles();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void removeDay() {
        if (listOfDays.getSize() != 0) {
            int num = listOfDays.getSize();
            num--;
            Day removedDay = listOfDays.getDay(num);
            listOfDays.removeDay(removedDay);
        }
    }

    private void saveFiles() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfDays);
            jsonWriter.close();
            System.out.println("Saved " + listOfDays.getSize() + " days to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }


    }

    public void loadFiles() {
        try {
            listOfDays = jsonReader.read();
            System.out.println("Loaded " + listOfDays.getSize() + " days from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: lets user define a new day
    public void doRecordNewDay() {
        String commandDay = null;

        boolean goOn = true;
        displayRecordingMenu();
        Day day = new Day();

        while (goOn) {
            commandDay = input.next();
            commandDay = commandDay.toLowerCase();
            processCommandDay(commandDay, day);


            if (commandDay.equals("p") || commandDay.equals("d")) {
                goOn = false;
            }
        }
    }

    // EFFECTS: displays the RECORD NEW DAY menu
    public void displayRecordingMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tf -> Record A New Food Intake");
        System.out.println("\te -> Record A New Exercise you did");
        System.out.println("\tc -> Calculate Calorie Deficit");
        System.out.println("\td -> Add this Day to the List Of Days");
        System.out.println("\tp -> Return to Previous Menu");
    }


    // MODIFIES: this
    // EFFECTS: processes user command for the RECORD NEW DAY menu
    public void processCommandDay(String commandDay, Day day) {

        if (commandDay.equals("f")) {
            Food f = doRecordFoods();
            day.addFood(f);

        } else if (commandDay.equals("e")) {
            Exercise e = doRecordExercises();
            day.addExercise(e);
        } else if (commandDay.equals("c")) {
            System.out.println("Calorie Deficit today is :" + day.getDayCalorieDeficit());
            displayRecordingMenu();
        } else if (commandDay.equals("d")) {
            listOfDays.addDay(day);
            System.out.println("Added to List Of Days Successfully!");
           // displayMenuForMain();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command for RECORD NEW EXERCISE OPTION in the RECORD NEW DAY menu
    public Exercise doRecordExercises() {
       // input2 = new Scanner(System.in);
        //input2.useDelimiter("\n");

        String command = null;
        int calsBurned = 0;
        Exercise e = new Exercise("", 0);

        System.out.println("Please Enter the Name of the Exercise That you've Done");
        command = input.next();
        e.setExerciseName(command);

        System.out.println("Please Enter the Calorie Burned From the Exercise");
        calsBurned = input.nextInt();
        e.setCaloriesBurned(calsBurned);

        System.out.println("Exercise Added Successfully!!");
        displayRecordingMenu();
        return e;
    }


    // MODIFIES: this
    // EFFECTS: processes user command for RECORD NEW FOOD OPTION in the RECORD NEW DAY menu
    public Food doRecordFoods() {
        Food f = new Food("", 0);

        System.out.println("Please Enter the Name of the Food That you Consumed");
        String command = input.next();
        f.setFoodName(command);

        System.out.println("Please Enter the Calorie of the Food");
        int calorie = input.nextInt();
        f.setFoodCalorie(calorie);

        System.out.println("Food Added Successfully!!");
        displayRecordingMenu();

        return f;

    }

    //EFFECTS: processes user command for VIEW PAST DAYS option in the MAIN MENU
    private void doViewPastDays() {
        boolean goOnView = true;
        String command2 = "";

        while (goOnView) {
            if (listOfDays.getSize() == 0) {
                System.out.println("You do not have any days to check!");
                goOnView = false;
            } else {
                Day viewDay = selectDay();
                displayForThatDay(viewDay);
                System.out.println("\nSelect from:");
                System.out.println("\tp -> return to main menu");
                System.out.println("\te -> view another day");

                command2 = input.next();

                if (command2.equals("p")) {
                    goOnView = false;
                }
            }
        }
    }


    //EFFECTS: processes user command to present the users the activities they've done on the day they selected on the
    //         VIEW PAST DAYS MENU
    public void displayForThatDay(Day viewDay) {
        List<Food> foodListThisDay = viewDay.getDailyFoodList();
        List<Exercise> exercisesListThisDay = viewDay.getExerciseList();

        System.out.println("Your Calorie Deficit for this day is" + viewDay.getDayCalorieDeficit());

        System.out.println("Here are the Food that you consumed :");
        for (Food f: foodListThisDay) {
            System.out.println(f.getFoodName() + "(" + f.getFoodCalorie() + ")");
        }

        System.out.println("Here are the Exercises that you've done");
        for (Exercise e: exercisesListThisDay) {
            System.out.println(e.getExerciseName() + "(" + e.getCaloriesBurned() + ")");
        }
    }

//    private void displayMenuForDays() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tf -> View Food");
//        System.out.println("\tv -> View Exercise");
//        System.out.println("\tr -> ");
//        System.out.println("\tq -> quit");
//    }


    // EFFECTS: helper function that indicates the lets and processes the day that the user wants to check. Allows users
    //          to see how many days they have recorded already
    private Day selectDay() {
        int selection = -1;

        while (!((0 < selection) && (selection <= listOfDays.getSize()))) {
            System.out.println("Which day would you like to check?");
            System.out.println("you currently have " + listOfDays.getSize() + " days available");

            selection = input.nextInt();
        }
        return listOfDays.getDay(selection - 1);
    }




    // MODIFIES: this
    // EFFECTS:  processes the "set a goal" option in the MAIN MENU, lets the users to input their current and ideal
    //           weight, as well as the number of days they plan to reach that goal, then informs the users the calorie
    //           deficit per day they need for that goal to happen.
    private void doGoalSetter() {
        String selection = "";  // force entry into loop

        System.out.println("please enter your current weight in kg");
        int current = input.nextInt();
        gs.setCurrentWeight(current);

        System.out.println("please enter your ideal weight in kg");
        int ideal = input.nextInt();
        while (ideal >= current) {
            System.out.println("Please enter an Ideal weight that is smaller than current weight!");
            ideal = input.nextInt();
        }
        if (ideal < current) {
            gs.setGoalWeight(ideal);
        }

        System.out.println("please enter the days that you plan to reach your goal");
        int days = input.nextInt();

        gs.setPlannedDaysReachGoal(days);

        System.out.println("To lose " + gs.kgPlanningToLose() + "kg in " + gs.getPlannedDaysReachGoal()
                + "days you need a calorie deficit of " + gs.getDeficitPerDay() + " per day on average");
    }


    public ListOfDays getListOfDays() {
        return this.listOfDays;
    }

    @Override
    public void printEvents(EventLog eventLog) {
        for (Event event: eventLog) {
            System.out.println(event.toString());
        }
    }


//    //HELPER
//    private int dayNumForThisDay() {
//        return listOfDays.getSize();
//    }

}







