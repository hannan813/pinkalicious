package edu.sdccd.cisc190;

public class Level {
    private int currentLevel;
    private final int maxLevel = 6; // Define the maximum number of levels

    public Level() {
        this.currentLevel = 1;
    }

    public void nextLevel() {
        if (currentLevel < maxLevel) { // Ensure the level doesn't exceed maxLevel
            currentLevel++;
        } else {
            System.out.println("You've reached the final level!");
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}