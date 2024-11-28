package edu.sdccd.cisc190;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    private Rectangle playerShape;
    private double speed = 5.0;  // Speed for the player

    public Player(double x, double y) {
        playerShape = new Rectangle(x, y, 30, 30);  // Create a rectangle for the player
        playerShape.setFill(Color.BLUE);  // Color of the player
    }

    // Method to move player by deltaX and deltaY
    public void move(double deltaX, double deltaY) {
        playerShape.setX(playerShape.getX() + deltaX);
        playerShape.setY(playerShape.getY() + deltaY);
    }

    // Moves the player to the right
    public void moveRight() {
        move(speed, 0);  // Move right by speed value
    }

    // Moves the player to the left
    public void moveLeft() {
        move(-speed, 0);  // Move left by speed value
    }

    // Moves the player upwards
    public void moveUp() {
        move(0, -speed);  // Move up by speed value
    }

    // Moves the player downwards
    public void moveDown() {
        move(0, speed);  // Move down by speed value
    }

    // Resets the player's position to the given x, y coordinates
    public void resetPosition(double x, double y) {
        playerShape.setX(x);
        playerShape.setY(y);
    }

    // Getter for the player's shape (Rectangle)
    public Node getShape() {
        return playerShape;
    }

    public void resetPosition() {

    }
}
