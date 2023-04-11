package ui;

import model.Day;
import model.Exercise;
import model.Food;
import model.ListOfDays;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ViewPastDaysWindow extends JFrame implements ActionListener {

    private JLabel label1;
    private JTextField field;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;


    private ListOfDays lod = WeightLosingGUI.getLod();

    public ViewPastDaysWindow() {
        super("Past Days");
        runViewPastDaysWindow();
    }

    public void runViewPastDaysWindow() {


        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));


        displayPastDays();
        setLocationRelativeTo(null);
        setVisible(true);
//        JButton btn = new JButton("Return To Previous Menu");
//        btn.setActionCommand("Previous Menu");
//        btn.addActionListener(this);

    }

    public void displayPastDays() {
        if (this.lod.getSize() == 0) {
            label1 = new JLabel("You Do Not Currently Have Any Days To Check");
            add(label1);
            pack();
        } else {
            presentActivities();
        }
    }

    public void presentActivities() {
        int numDays = this.lod.getSize();

        for (int i = 0; i < numDays; i++) {
            Day day = lod.getDay(i);
            JLabel labelDay = new JLabel("Day " + i);
            add(labelDay);
            List<Food> foodList = day.getDailyFoodList();
            List<Exercise> exerciseList = day.getExerciseList();
            for (Food f : foodList) {
                String name = f.getFoodName();
                int calorie = f.getFoodCalorie();
                JLabel labelFood = new JLabel(name + " : " + calorie);
                add(labelFood);
            }
            for (Exercise e : exerciseList) {
                String name = e.getExerciseName();
                int calorie = e.getCaloriesBurned();
                JLabel labelExercise = new JLabel(name + " : " + calorie);
                add(labelExercise);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

}