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
    }
}