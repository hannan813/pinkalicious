package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Player {
    private int x, y;
    private int m, n, L;
    private boolean left, right, up, down;
    private boolean isInsideGameOutline;


    int width, height;

    public Player(int startX, int startY, int l) {
        this.x = startX;
        this.y = startY;
    }
    public boolean isInsideGameOutline(int x, int y) {
        Polygon boundary = new Polygon();
        boundary.getPoints().addAll(
                (double) m, (double) n,
                (double) (m + 3 * L), (double) n,
                (double) (m + 3 * L), (double) (n + 6 * L),
                (double) (m + 4 * L), (double) (n + 6 * L),
                (double) (m + 4 * L), (double) (n + L),
                (double) (m + 11 * L), (double) (n + L),
                (double) (m + 11 * L), (double) n,
                (double) (m + 16 * L), (double) n,
                (double) (m + 16 * L), (double) (n + 7 * L),
                (double) (m + 13 * L), (double) (n + 7 * L),
                (double) (m + 13 * L), (double) (n + L),
                (double) (m + 12 * L), (double) (n + L),
                (double) (m + 12 * L), (double) (n + 6 * L),
                (double) (m + 5 * L), (double) (n + 6 * L),
                (double) (m + 5 * L), (double) (n + 7 * L),
                (double) m, (double) (n + 7 * L)
        );
        return boundary.contains(x, y);  // This will check if the point (x, y) is inside the polygon
    }
    public void update() {
        int speed = 5;  // Mario’s movement speed

        // Store Mario's new position
        int newX = x;
        int newY = y;

        // Check for Mario's movement (left, right, up, down)
        if (left) newX -= speed;  // Try to move left
        if (right) newX += speed;  // Try to move right
        if (up) newY -= speed;  // Try to move up
        if (down) newY += speed;  // Try to move down

        // Check if the new position is inside the polygon
        if (isInsideGameOutline(newX, newY)) {
            x = newX;  // If inside, update Mario’s position
            y = newY;
        }
        // If Mario is outside, don’t update his position (he stays in the old position)
    }
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, 20, 20);
    }

    public boolean checkCollision(Enemy enemy) {
        return x < enemy.getX() + 20 && x + 20 > enemy.getX()
                && y < enemy.getY() + 20 && y + 20 > enemy.getY();
    }

    public void respawn() {
        x = 100;
        y = 350;
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> left = true;
            case RIGHT -> right = true;
            case UP -> up = true;
            case DOWN -> down = true;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> left = false;
            case RIGHT -> right = false;
            case UP -> up = false;
            case DOWN -> down = false;
        }
    }
}
