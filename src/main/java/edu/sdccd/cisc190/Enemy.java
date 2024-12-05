package edu.sdccd.cisc190;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Enemy {
    private int x, y;
    private boolean movingRight;
    //    private static final int SPEED = 10;
    private static final int MIN_X = 240; // Left boundary
    private static final int MAX_X = 530; // Right boundary
    private static final int WIDTH = 20; // Enemy width
    private static final Color ENEMY_COLOR = Color.SADDLEBROWN; // Cache color
    private final Game game;


    public Enemy(int index, Game game) {
        // Precompute initial position and direction based on index
        this.x = (index % 2 == 0) ? 300 : 500;
        this.y = 160 + index * 40;
        this.movingRight = index % 2 == 0;
        this.game = game;


    }


    public void update() {
        // Move and toggle direction at boundaries
        int SPEED = game.getSpeed();
        x += movingRight ? SPEED : -SPEED;
        if (x <= MIN_X || x >= MAX_X) {
            movingRight = !movingRight;
        }
    }


    public void render(GraphicsContext gc) {
        // Draw enemy
        gc.setFill(ENEMY_COLOR);
        gc.fillOval(x, y, WIDTH, WIDTH);
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }
}
