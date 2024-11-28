package edu.sdccd.cisc190;

import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Enemy {
    private Circle enemy;  // Circle represents the enemy

    // Constructor with parameters for position and color
    public Enemy(double x, double y, double radius, Color color) {
        enemy = new Circle(x, y, radius, color);
    }

    // Constructor to use a default size and color (if needed)
    public Enemy(double x, double y) {  // Changed to double for consistency
        enemy = new Circle(x, y, 20, Color.RED);  // Default size and color
    }

    // Getter for the enemy shape (Circle)
    public Node getShape() {
        return enemy;
    }

    // Linear movement (back and forth) along a line
    public void moveLinearly(double startX, double startY, double endX, double endY, double duration) {
        Line line = new Line(startX, startY, endX, endY);
        PathTransition transition = new PathTransition(Duration.seconds(duration), line, enemy);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }

    // Circular movement around a center
    public void moveInCircle(double centerX, double centerY, double radius, double duration) {
        Path circlePath = new Path();
        circlePath.getElements().addAll(
                new javafx.scene.shape.MoveTo(centerX + radius, centerY),
                new javafx.scene.shape.ArcTo(radius, radius, 0, centerX - radius, centerY, false, true),
                new javafx.scene.shape.ArcTo(radius, radius, 0, centerX + radius, centerY, false, true)
        );
        PathTransition transition = new PathTransition(Duration.seconds(duration), circlePath, enemy);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play();
    }

    // Simple movement update (for this example, just moves linearly)
    public void updateMovement() {
        // Example: make the enemy move in a simple back-and-forth motion
        moveLinearly(enemy.getCenterX(), enemy.getCenterY(), enemy.getCenterX() + 100, enemy.getCenterY(), 2);
    }

    public Node getEnemy() {
        return enemy;  // Return the Circle (enemy)
    }}


