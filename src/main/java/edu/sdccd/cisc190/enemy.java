package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class enemy {

    private int x, y;

    public enemy(int startX, int startY) {
        x = startX;
        y = startY;
    }

    public void move(int dx) {
        x += dx;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillOval(x, y, 20, 20);
    }
}
