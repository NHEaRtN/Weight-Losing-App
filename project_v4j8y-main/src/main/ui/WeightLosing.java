package ui;

import model.Day;
import model.ListOfDays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WeightLosing {

    public static void main(String[] args) {
        new WeightLosingGUI();
    }
}