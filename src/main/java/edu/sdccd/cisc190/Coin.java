package edu.sdccd.cisc190;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coin {

    private int x, y;
    private Circle circle;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        circle = new Circle(x, y, 10);
        circle.setFill(Color.YELLOW);
    }

    public Circle getCircle() {
        return circle;
    }

    public Node getShape() {
        return null;
    }
}
