package edu.sdccd.cisc190;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private Pane pane;
    private List<Enemy> enemies;

    public Level() {
        pane = new Pane();
        enemies = new ArrayList<>();
    }

    public Pane getPane() {
        return pane;
    }

    public void setupLevel1() {
        pane.setStyle("-fx-background-color: cyan;");
        drawGrid();  // Corrected this line

        // Safe zones
        Rectangle startZone = new Rectangle(50, 200, 50, 150);
        startZone.setFill(Color.LIGHTGREEN);
        pane.getChildren().add(startZone);

        Rectangle endZone = new Rectangle(700, 200, 50, 150);
        endZone.setFill(Color.LIGHTGREEN);
        pane.getChildren().add(endZone);

        // Enemies
        Enemy enemy1 = new Enemy(400, 175, 10, Color.BLUE);
        enemy1.moveLinearly(400, 175, 400, 375, 2);  // Example linear movement
        pane.getChildren().add(enemy1.getShape());  // Corrected this line
        enemies.add(enemy1);

        Enemy enemy2 = new Enemy(250, 275, 10, Color.BLUE);
        enemy2.moveLinearly(250, 200, 250, 350, 2.5);  // Another linear movement
        pane.getChildren().add(enemy2.getShape());  // This line is correct
        enemies.add(enemy2);
    }

    public void setupLevel2() {
        pane.setStyle("-fx-background-color: cyan;");
        drawGrid();  // Corrected this line

        // Safe zones
        Rectangle startZone = new Rectangle(50, 200, 50, 150);
        startZone.setFill(Color.LIGHTGREEN);
        pane.getChildren().add(startZone);

        Rectangle endZone = new Rectangle(700, 200, 50, 150);
        endZone.setFill(Color.LIGHTGREEN);
        pane.getChildren().add(endZone);

        // Enemies with circular movement
        Enemy enemy1 = new Enemy(400, 275, 10, Color.BLUE);
        enemy1.moveInCircle(400, 275, 50, 3);  // Circular movement
        pane.getChildren().add(enemy1.getEnemy());
        enemies.add(enemy1);

        Enemy enemy2 = new Enemy(300, 275, 10, Color.BLUE);
        enemy2.moveLinearly(300, 200, 300, 350, 2);  // Linear movement
        pane.getChildren().add(enemy2.getEnemy());
        enemies.add(enemy2);
    }

    public void setupLevel3() {
        pane.setStyle("-fx-background-color: cyan;");
        drawGrid();  // Corrected this line

        // Safe zones
        Rectangle startZone = new Rectangle(50, 100, 50, 300);
        startZone.setFill(Color.LIGHTGREEN);
        pane.getChildren().add(startZone);

        Rectangle endZone = new Rectangle(700, 100, 50, 300);
        endZone.setFill(Color.LIGHTGREEN);
        pane.getChildren().add(endZone);

        // Enemies with mixed movement
        Enemy enemy1 = new Enemy(250, 275, 10, Color.BLUE);
        enemy1.moveInCircle(250, 275, 40, 2);  // Circular movement
        pane.getChildren().add(enemy1.getEnemy());
        enemies.add(enemy1);

        Enemy enemy2 = new Enemy(400, 275, 10, Color.BLUE);
        enemy2.moveLinearly(400, 150, 400, 400, 1.8);  // Linear movement
        pane.getChildren().add(enemy2.getEnemy());
        enemies.add(enemy2);
    }

    // Method to draw the grid
    private void drawGrid() {
        for (int i = 0; i < 800; i += 50) {
            Rectangle line = new Rectangle(i, 0, 1, 500);
            line.setFill(Color.BLACK);
            pane.getChildren().add(line);
        }
        for (int j = 0; j < 500; j += 50) {
            Rectangle line = new Rectangle(0, j, 800, 1);
            line.setFill(Color.BLACK);
            pane.getChildren().add(line);
        }
    }

    // Method to clear the level when transitioning
    public void clearLevel() {
        pane.getChildren().clear();
        enemies.clear();
    }
}
