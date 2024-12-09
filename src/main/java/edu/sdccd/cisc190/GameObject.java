package edu.sdccd.cisc190;


import javafx.scene.canvas.GraphicsContext;

// TODO: Ensure you add Javadocs for each public method in this class.
    // Examples are seen in Classes: EndOfYearProject, Enemy, & Game

public class GameObject {
    protected int x, y;  // Position variables


    // Constructor
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject() { // Default constructor
    }

    // Abstract update method to be overridden in subclasses
    public void update() {
        // Default implementation or empty if subclasses will provide their own
    }


    // Abstract render method to be overridden in subclasses
    public void render(GraphicsContext gc) {
        // Default implementation or empty if subclasses will provide their own
    }


    // Getters for position
    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }
}