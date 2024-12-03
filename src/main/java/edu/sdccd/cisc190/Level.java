package edu.sdccd.cisc190;


public class Level {
    private int currentLevel;


    public Level() {
        this.currentLevel = 1;
    }


    public void nextLevel() {
        currentLevel++;
    }


    public int getCurrentLevel() {
        return currentLevel;
    }
}