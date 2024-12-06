package edu.sdccd.cisc190;

import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<String> scores;
    private final String fileName;

    public Leaderboard(String fileName) {
        this.fileName = fileName;
        this.scores = new ArrayList<>();
        loadScores();  // Load scores from the file when the leaderboard is created
    }

    public ArrayList<String> getScores() {
        return scores;  // Return the list of scores
    }

    public void addScore(String score) {
        scores.add(score);
        saveScores();  // Save the leaderboard to the file after adding a new score
    }

    public void display() {
        // Display the leaderboard (you can print to console or update UI)
        for (String score : scores) {
            System.out.println(score);  // Print each score
        }
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { //Block of code to try
            for (String score : scores) {
                writer.write(score);  // Write each score to the file
                writer.newLine();  // New line after each score
            }
        } catch (IOException e) {
            System.out.println("Error saving leaderboard: " + e.getMessage()); //Block of code to handle error
        }
    }

    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) { //Block of code to try
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);  // Add each line (score) to the scores list
            }
        } catch (IOException e) { //Block of code to handle error
            System.out.println("Error loading leaderboard: " + e.getMessage());
        }
    }
}
