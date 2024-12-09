package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents an enemy in the game, which moves within a defined range.
 */
public class Enemy extends GameObject {
    public static final int MAX_X = 530;  // Right boundary
    public static final int MIN_X = 240;  // Left boundary
    private boolean movingRight;  // Direction flag
    private final Game game;      // Reference to the Game object

    /**
     * Constructs an Enemy with a specified index and position.
     *
     * @param index The index of enemy. Determines starting position.
     * @param i The y position of enemy.
     * @param game Game object to access game information (ex. speed).
     */
    // Constructor
    public Enemy(int index, int i, Game game) {
        super((index % 2 == 0) ? 300 : 500, 160 + index * 40); // Set initial position
        this.movingRight = index % 2 == 0;  // Alternate movement direction based on index
        this.game = game;  // Store reference to the Game object
    }

    /**
     * Positions & Movement of enemy are updated.
     */
    @Override
    public void update() {
        // Use the current SPEED from the Game class
        int currentSpeed = game.getSpeed();

        // Move the enemy left or right
        if (movingRight) {
            this.x += currentSpeed;  // Use inherited x
        } else {
            this.x -= currentSpeed;  // Use inherited x
        }

        // Reverse direction if hitting boundary
        if (x <= MIN_X || x >= MAX_X) {
            movingRight = !movingRight;
        }

        // Keep the enemy within bounds by constraining x to [MIN_X, MAX_X]
        if (x < MIN_X) x = MIN_X;
        if (x > MAX_X) x = MAX_X;
    }
    /**
     * Renders the enemy on the given graphics context.
     *
     * @param gc The graphics context to render the enemy on.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.SADDLEBROWN);  // Set the color for the Goomba
        gc.fillOval(x, y, 20, 20);  // Draw the Goomba (using x, y from GameObject)
    }
}
