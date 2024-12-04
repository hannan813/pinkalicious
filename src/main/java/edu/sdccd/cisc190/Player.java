package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.geometry.Rectangle2D;

public class Player {
    private final int speed = 5;
    public boolean movingLeft;
    public boolean movingRight;
    int x;
    int y;
    private int m;
    private int n;
    private int L;
    private boolean left, right, up, down;

    public Player(int startX, int startY, int size) {
        this.x = startX;
        this.y = startY;
        this.L = size;
        this.m = 72;
        this.n = 115;
    }

    public Player(int startX, int startY) {
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

    public void update() {
        int newX = x, newY = y;

        if (left) newX -= speed;
        if (right) newX += speed;
        if (up) newY -= speed;
        if (down) newY += speed;

        if (isInsideGameOutline(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, L, L);
    }

    public boolean checkCollision(Enemy enemy) {
        Rectangle2D playerBounds = new Rectangle2D(x, y, L, L);
        Rectangle2D enemyBounds = new Rectangle2D(enemy.getX(), enemy.getY(), 40, 40);
        return playerBounds.intersects(enemyBounds);
    }

    public void respawn() {
        x = 100;
        y = 350;
    }

    public void move(int i, int i1) {

    }
}