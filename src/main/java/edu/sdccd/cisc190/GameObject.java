package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
    protected int x, y; // Position variables
    protected Image sprite; // Image for the game object

    // Constructor
    public GameObject(int x, int y, String spritePath) {
        this.x = x;
        this.y = y;
        this.sprite = new Image(spritePath); // Load the image
    }

    // Update method to be overridden in subclasses
    public abstract void update();

    // Render method to be overridden in subclasses
    public void render(GraphicsContext gc) {
        // Default: Draw the sprite
        gc.drawImage(sprite, x, y, 50, 50); // Draw the image at (x, y), size 50x50
    }

    // Getters for position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
