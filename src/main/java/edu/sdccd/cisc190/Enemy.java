package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy {
    private int x, y;
    private boolean movingRight;
    private static final int SPEED = 4;

    public Enemy(int index) {
        this.x = (index % 2 == 0) ? 300 : 500;
        this.y = 100 + index * 40;
        this.movingRight = index % 2 == 0;
    }

    public void update() {
        if (movingRight) x += SPEED;
        else x -= SPEED;

        // Reverse direction if hitting boundary
        if (x < 100 || x > 700) movingRight = !movingRight;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BROWN); // the coding for the goombas so this is where the image wouldgo
        gc.fillOval(x, y, 20, 20);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

