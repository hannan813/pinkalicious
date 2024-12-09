package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

// TODO: Delete this class. This class seems to not be in use and is not necessary since it is not shown in the game.

public class Coin {
    private int x, y;
    private boolean collected;
    private Image sprite;

    // Constructor to initialize the coin with a position
    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        this.collected = false;
        this.sprite = new Image(getClass().getResource("/images/coin.png").toExternalForm());  // Load coin image
    }

    // Getter for x position
    public int getX() {
        return x;
    }

    // Getter for y position
    public int getY() {
        return y;
    }

    // Check if coin is collected
    public boolean isCollected() {
        return collected;
    }

    // Collect the coin (change its state to collected)
    public void collect() {
        this.collected = true;
    }

    // Render the coin (draw the coin if it is not collected)
    public void render(GraphicsContext gc) {
        if (!collected) {
            gc.drawImage(sprite, x, y);  // Draw the coin image
        }
    }
}
