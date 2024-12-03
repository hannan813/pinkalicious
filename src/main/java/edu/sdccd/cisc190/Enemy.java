package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy {
    public static final int MAX_X = 530; // Right boundary;
    public static final int MIN_X =240 ;
    private int x, y;
    private boolean movingRight;
    private static final int SPEED = 5;
    private static final int GRID_WIDTH = 7; // Adjust the grid width as per your game layout

    public Enemy(int index) {
        this.x = (index % 2 == 0) ? 300 : 500;
        this.y = 160 + index * 40;
        this.movingRight = index % 2 == 0;
    }

    public void update() {
        // Move the enemy left or right
        if (movingRight) x += SPEED;
        else x -= SPEED;

        // Reverse direction if hitting boundary
        if (x <= MIN_X || x >= MAX_X) {
            movingRight = !movingRight;
        }

        // Keep the enemy within bounds by constraining x to [MIN_X, MAX_X]
        if (x < MIN_X) x = MIN_X;
        if (x > MAX_X) x = MAX_X;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.SADDLEBROWN); // The coding for the Goombas (where the image would go)
        gc.fillOval(x, y, 20, 20); // Draw the enemy (Goomba)
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
