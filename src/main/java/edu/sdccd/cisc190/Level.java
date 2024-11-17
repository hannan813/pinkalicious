package edu.sdccd.cisc190;

public class Level {

    private int levelNumber;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void nextLevel() {
        levelNumber++;
    }
}
