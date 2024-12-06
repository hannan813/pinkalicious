package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy extends GameObject {
    public static final int MAX_X = 530;  // Right boundary
    public static final int MIN_X = 240;  // Left boundary
    private boolean movingRight;  // Direction flag
    private static final int SPEED = 3;  // Speed of the enemy

    // Constructor
    public Enemy(int index, Game game) {
        super( (index % 2 == 0) ? 300 : 500, 160 + index * 40); // Set initial position
        this.movingRight = index % 2 == 0;  // Alternate movement direction based on index
    }

    @Override
    public void update() {
        // Move the enemy left or right
        if (movingRight) {
            this.x += SPEED;  // Use inherited x
        } else {
            this.x -= SPEED;  // Use inherited x
        }

        // Reverse direction if hitting boundary
        if (x <= MIN_X || x >= MAX_X) {
            movingRight = !movingRight;
        }

        // Keep the enemy within bounds by constraining x to [MIN_X, MAX_X]
        if (x < MIN_X) x = MIN_X;
        if (x > MAX_X) x = MAX_X;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.SADDLEBROWN);  // Set the color for the Goomba
        gc.fillOval(x, y, 20, 20);  // Draw the Goomba (using x, y from GameObject)
    }
}
