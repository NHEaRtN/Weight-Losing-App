package ui;

import model.Day;
import model.ListOfDays;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WeightLosingGUI extends JFrame implements ActionListener {

    private JLabel label;
    private JTextField field;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/listofdays.json";


    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static ListOfDays listOfDays;


    public WeightLosingGUI() {
        super("Weight Losing App");
        listOfDays = new ListOfDays();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        intializeGraphics();
//        initializeExit();
//        initializeInteraction();


    }

    public static ListOfDays getLod() {
        return listOfDays;
    }

    public void intializeGraphics() {

        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        setOptions();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void setOptions() {
        JButton btn = new JButton("Load Past Days' Activities");
        btn.setActionCommand("Load Activities");
        btn.addActionListener(this);

        JButton btn2 = new JButton("Show Past Days' Activities");
        btn2.setActionCommand("Present Activities");
        btn2.addActionListener(this);

        JButton btn3 = new JButton("Record Another Day");
        btn3.setActionCommand("Record New Day");
        btn3.addActionListener(this);

        JButton btn4 = new JButton("Save These Days");
        btn4.setActionCommand("save");
        btn4.addActionListener(this);

        JButton btn5 = new JButton("Remove The Last Day");
        btn5.setActionCommand("remove");
        btn5.addActionListener(this);

        add(btn);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
    }


//    public void initializeExit() {
//        //可视化, option of saving the actions performed this time.
//    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Load Activities")) {
            readFile();
        } else if (e.getActionCommand().equals("Present Activities")) {
//            setVisible(false);
            commandPastActivities();
        } else if (e.getActionCommand().equals("Record New Day")) {
            new RecordNewDay();
        } else if (e.getActionCommand().equals("save")) {
            saveToFile();
        } else if (e.getActionCommand().equals("remove")) {
            removeLastDay();
        }
    }

    private void removeLastDay() {
        if (listOfDays.getSize() != 0) {
            int num = listOfDays.getSize();
            num--;
            Day removedDay = listOfDays.getDay(num);
            listOfDays.removeDay(removedDay);
        }
    }

    private void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfDays);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            label = new JLabel("Can't save to file");
            add(label);
        }
    }

    private void readFile() {
        try {
            listOfDays = jsonReader.read();
        } catch (IOException e) {
            JLabel label = new JLabel("Unable to read from file: " + JSON_STORE);
            add(label);
        }
    }

    public void commandPastActivities() {
        new ViewPastDaysWindow();
    }
}

