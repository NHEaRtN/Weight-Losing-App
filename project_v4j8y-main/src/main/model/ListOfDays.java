package model;

import model.exception.NoSuchADayException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListOfDays implements Writable {
    private LinkedList<Day> dayList;
    //private int lodCount;


    public ListOfDays() {
        this.dayList = new LinkedList<>();
    }

    public int getTotalCalorieDeficit() {
        int deficitCount = 0;
        for (Day d : dayList) {
            deficitCount += d.getDayCalorieDeficit();
        }
        return deficitCount;
    }

    public List<Day> getListOfDays() {
        return this.dayList;
    }

    public void addNewDay() {
        Day day = new Day();
        dayList.add(day);
    }


    public void addDay(Day d) {

        EventLog.getInstance().logEvent(new Event("Added a New Day: Day" + dayList.size()));
        dayList.add(d);
    }


    public void removeDay(Day day) {
        EventLog.getInstance().logEvent(new Event("Removed a Day: Day " + dayList.size()));
        dayList.remove(day);
    }

    public int getSize() {
        return dayList.size();
    }

    public Day getDay(int dayNum) {
        return dayList.get(dayNum);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
//        json.put("lodCount", lodCount);
        json.put("List Of Days", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Day day : dayList) {
            jsonArray.put(day.toJson());
        }

        return jsonArray;
    }
}


