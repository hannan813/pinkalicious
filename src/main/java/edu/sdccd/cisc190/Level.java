package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Level {
    private int currentLevel;
    private final int maxLevel = 7; // Define the maximum number of levels

    public Level() {
        this.currentLevel = 1;
    }

    // Go to the next level
    public void nextLevel() {
        if (currentLevel < maxLevel) { // Ensure the level doesn't exceed maxLevel
            currentLevel++;
        } else {
            System.out.println("You've reached the final level!");
        }
    }

    // Get the current level
    public int getCurrentLevel() {
        return currentLevel;
    }

    // Blank level to show the congratulations screen
    public void loadCongratulationsLevel(GraphicsContext gc) {
        gc.setFill(Color.WHITE);  // Set background to white or any color you want
        gc.fillRect(0, 0, 800, 600);  // Adjust size to your canvas
    }
}