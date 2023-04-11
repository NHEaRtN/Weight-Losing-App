package ui;

import model.Day;
import model.Exercise;
import model.Food;
import model.ListOfDays;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RecordNewDay extends JFrame implements ActionListener {

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;

    Day day = new Day();

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    public RecordNewDay() {
        super("Past Days");
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        JButton buttonFood = new JButton("Record New Food");
        buttonFood.setActionCommand("record new food");
        buttonFood.addActionListener(this);

        JButton buttonExercise = new JButton("Record New Exercise");
        buttonExercise.setActionCommand("record new exercise");
        buttonExercise.addActionListener(this);

        JButton buttonSaveDay = new JButton("Save This Day");
        buttonSaveDay.setActionCommand("save day");
        buttonSaveDay.addActionListener(this);

        add(buttonFood);
        add(buttonExercise);
        add(buttonSaveDay);

        runRecordNewDay();
    }

    public void runRecordNewDay() {


        label1 = new JLabel("Food Name");
        field1 = new JTextField(15);
        label2 = new JLabel("Food Calories");
        field2 = new JTextField(15);

        label3 = new JLabel("Exercise Name");
        field3 = new JTextField(15);
        label4 = new JLabel("Exercise Calories");
        field4 = new JTextField(15);



        add(label1);
        add(field1);
        add(label2);
        add(field2);
        add(label3);
        add(field3);
        add(label4);
        add(field4);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("record new food")) {
            String foodName = field1.getText();
            String foodCal = field2.getText();
            int calories = Integer.parseInt(foodCal);

            Food f = new Food(foodName, calories);
            day.addFood(f);
        }
        if (e.getActionCommand().equals("record new exercise")) {
            String exerciseName = field3.getText();
            String exerciseCal = field4.getText();
            int calories = Integer.parseInt(exerciseCal);

            Exercise exercise = new Exercise(exerciseName, calories);
            day.addExercise(exercise);
        }
        if (e.getActionCommand().equals("save day")) {
            setVisible(false);
            ListOfDays lod = WeightLosingGUI.getLod();
            lod.addDay(day);
        }
    }
}
