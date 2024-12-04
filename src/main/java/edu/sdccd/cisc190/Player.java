package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.geometry.Rectangle2D;

public class Player extends GameObject {
    private final int speed = 5;
    boolean movingLeft;
    boolean movingRight;
    private int L;
    private int m;
    private int n;
    private boolean left, right, up, down;

    // Constructor (inherits x, y from GameObject)
    public Player(int startX, int startY, int size) {
        super(startX, startY);  // Call the GameObject constructor to set x, y
        this.L = size;
        this.m = 72;
        this.n = 115;
    }

    // Constructor (optional, if needed for a different initialization)
    public Player(int startX, int startY) {
        super(startX, startY);  // Call the GameObject constructor to set x, y
    }

    // Handle key press events
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> left = true;
            case RIGHT -> right = true;
            case UP -> up = true;
            case DOWN -> down = true;
        }
    }

    // Handle key release events
    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> left = false;
            case RIGHT -> right = false;
            case UP -> up = false;
            case DOWN -> down = false;
        }
    }

    // Check if the player is inside the game boundary
    public boolean isInsideGameOutline(int x, int y) {
        Polygon boundary = new Polygon(
                m, n, m + 3 * L, n, m + 3 * L, n + 6 * L,
                m + 4 * L, n + 6 * L, m + 4 * L, n + L,
                m + 11 * L, n + L, m + 11 * L, n,
                m + 16 * L, n, m + 16 * L, n + 7 * L,
                m + 13 * L, n + 7 * L, m + 13 * L, n + L,
                m + 12 * L, n + L, m + 12 * L, n + 6 * L,
                m + 5 * L, n + 6 * L, m + 5 * L, n + 7 * L,
                m, n + 7 * L
        );
        return boundary.contains(x, y);
    }

    // Update player position based on key input
    public void update() {
        int newX = x, newY = y;

        if (left) newX -= speed;
        if (right) newX += speed;
        if (up) newY -= speed;
        if (down) newY += speed;

        if (isInsideGameOutline(newX, newY)) { // Conditional to ensure valid movement
            x = newX;
            y = newY;
        }
    }

    // Render the player (draw player at x, y position)
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, L, L);
    }

    // Check for collision with enemy
   
    // Respawn player to a starting position
    public void respawn() {
        x = 100;
        y = 350;
    }

    // Unused method (remove or implement as needed)
    public void move(int i, int i1) {
    }

    public boolean checkCollision(Enemy enemy) {
        return x < enemy.getX() + 20 && x + 20 > enemy.getX()
                && y < enemy.getY() + 20 && y + 20 > enemy.getY();
    }

}
