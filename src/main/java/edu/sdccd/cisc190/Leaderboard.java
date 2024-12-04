package edu.sdccd.cisc190;

import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<String> scores;

    public Leaderboard() {
        this.scores = new ArrayList<>();
    }

    public void addScore(String score) {
        scores.add(score);
    }

    public void display() {
        // Display the leaderboard (could be a simple console print or GUI)
        // For now, let's just print the scores
        for (String score : scores) {
            System.out.println(score);
        }
    }

    // Fix the getScores method to return the actual scores list
    public ArrayList<String> getScores() {
        return scores;  // Return the scores list, not null
    }
}