package edu.sdccd.cisc190;

import java.io.*;
import java.util.*;

public class Leaderboard {
    private final List<String> scores;
    private final String filename;

    public Leaderboard(String filename) {
        this.filename = filename;
        this.scores = new ArrayList<>();
        loadScores();
    }

    // Load scores from the file
    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save scores to the file
    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String score : scores) {
                writer.write(score);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a new score to the leaderboard
    public void addScore(String name, int time, int fails) {
        String score = String.format("%s - Time: %d seconds, Fails: %d", name, time, fails);
        scores.add(score);
        saveScores();
    }

    // Get the leaderboard scores
    public List<String> getScores() {
        return scores;
    }

    public void addScore(String s) {
    }
}