package edu.sdccd.cisc190;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class MainMenu extends Application {
    private int x = 100, y = 350;
    private int level = 1, fails = 0, coins = 0;
    private boolean left = false, up = false, down = false, right = false;
    private boolean repeat = true;

    private int m = 72, n = 115, L = 40;
    private int[] xPoints = {m, m + 3 * L, m + 3 * L, m + 4 * L, m + 4 * L, m + 11 * L, m + 11 * L, m + 16 * L, m + 16 * L, m + 13 * L, m + 13 * L, m + 12 * L, m + 12 * L, m + 5 * L, m + 5 * L, m};
    private int[] yPoints = {n, n, n + 6 * L, n + 6 * L, n + L, n + L, n, n, n + 7 * L, n + 7 * L, n + L, n + L, n + 6 * L, n + 6 * L, n + 7 * L, n + 7 * L};
    private int numPoints = 16;
    // Declare Goomba variables here
    private int[] goombaX = {m + 120 + 8 * 40, m + 120 + 8 * 40, m + 120 + 8 * 40, m + 120 + 8 * 40, m + 120 + 8 * 40};
    private int[] goombaY = {n + 1 * 40, n + 2 * 40, n + 3 * 40, n + 4 * 40, n + 5 * 40};
    private int goombaSpeed = 2;  // How fast the Goombas move
    private boolean fwrd = true;  // Direction control for Goombas

    private Canvas canvas;
    private AnimationTimer gameLoop;

    public static void main(String[] args) {
        launch(args);
    }

    public static String getAppName() {
        return "EndOfYearProject";
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the canvas and scene
        canvas = new Canvas(800, 500);
        Group root = new Group(canvas);
        Scene scene = new Scene(root);

        // Key event handlers
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);

        // Set up the game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();

        // Set up the stage
        primaryStage.setTitle("End of Year Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean isInsideGameOutline(int x, int y) {
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

        return boundary.contains(x, y);
    }
    // Adding a direction array for Goombas to control movement direction (right or left)
    private boolean[] goombaDirection = new boolean[goombaX.length]; // true for right, false for left

    private void initializeGoombas() {
        // Initialize Goombas' starting directions
        for (int i = 0; i < goombaX.length; i++) {
            // Odd-indexed Goombas start moving from the left (false)
            // Even-indexed Goombas start moving from the right (true)
            goombaDirection[i] = (i % 2 == 0);  // true for right, false for left
        }
    }

    private void update() {
        if (level == 1) {
            int speed = 3;  // Adjust Mario's speed

            // Try moving in each direction, but only if the next position is inside the polygon
            if (left && isInsideGameOutline(x - speed, y)) {
                x -= speed;  // Move left if inside
            }
            if (right && isInsideGameOutline(x + speed, y)) {
                x += speed;  // Move right if inside
            }
            if (up && isInsideGameOutline(x, y - speed)) {
                y -= speed;  // Move up if inside
            }

            // Adjusting the downward movement check
            if (down && isInsideGameOutline(x, y + speed + 20)) {  // Account for Mario's height (20px)
                y += speed;  // Move down if inside
            }
            // Adjusting the downward movement check
            if (down && isInsideGameOutline(x, y + speed + 20)) {  // Account for Mario's height (20px)
                y += speed;  // Move down if inside
            }

            // Move Goombas with alternating direction logic
            for (int i = 0; i < goombaX.length; i++) {
                // If Goomba is moving right
                if (goombaDirection[i]) {
                    goombaX[i] += goombaSpeed;
                }
                // If Goomba is moving left
                else {
                    goombaX[i] -= goombaSpeed;
                }

                // Check if the Goomba hits the boundary and change direction
                if (goombaX[i] <= m + 120 || goombaX[i] >= m + 120 + 8 * 40) {
                    goombaDirection[i] = !goombaDirection[i];  // Toggle direction
                }
            }
            // Additional conditions like level completion can go here
            if (x > m + 520) {
                level++;  // Move to the next level when Mario passes a certain point
            }
        }
    }

    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTSKYBLUE);  // Background color
        gc.fillRect(0, 0, 800, 500);

        gc.setFill(Color.BLACK);  // Scoreboard background
        gc.fillRect(0, 0, 800, 50);

        // Set the font size and type
        gc.setFont(javafx.scene.text.Font.font("TIMES NEW ROMAN", 50));
        gc.setFill(Color.WHITE);  // Text color
        gc.fillText("Level: " + level, 3, 43);
        gc.fillText("Fails: " + fails, 620, 43);

        // Create a Polygon for the border
        Polygon border = new Polygon();
        border.getPoints().addAll(
                (double) m, (double) n, // Starting point
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

        // Set stroke color and width for the outline
        border.setStroke(Color.BLACK);
        border.setFill(Color.TRANSPARENT); // Transparent fill for just the outline
        border.setStrokeWidth(1);

        // Add the polygon to the scene (root group)
        Group root = (Group) canvas.getParent();
        root.getChildren().add(border);

        // Set up green tube color
        Color tubeColor = Color.color(144 / 255f, 238 / 255f, 144 / 255f);  // Green tube color
        gc.setFill(tubeColor);
        gc.fillRect(m, n, 120, 280);  // First green tube
        gc.fillRect(m + 520, n, 120, 280);  // Second green tube

        Color gridColor = Color.color(196 / 255f, 164 / 255f, 132 / 255f);  // Brown color

        for (int i = 0; i < 7; i++) {  // 8 rows of grid
            for (int j = 0; j < 10; j++) {  // 10 columns of grid
                int xPos = m + 120 + (j * 40);  // X position for each grid cell
                int yPos = n + (i * 40);  // Y position for each grid cell

                if (j == 0 && (i >= 0 && i <= 5)) {
                    continue;
                }
                if (j == 9 && (i >= 1 && i <= 6)) {
                    continue;
                }
                if ((i == 0 && j < 8) || (i == 6 && j >= 2)) {
                    continue; // Skip these squares
                }
                if ((i + j) % 2 == 0) {
                    gc.setFill(gridColor);  // Brown
                } else {
                    gc.setFill(Color.WHITE);  // White
                }

                gc.fillRect(xPos, yPos, 40, 40);  // Fill the grid cell
            }
        }

        // Draw Goombas
        gc.setFill(Color.BLACK);

// Draw Goombas using their updated positions
        for (int i = 0; i < goombaX.length; i++) {
            gc.fillOval(goombaX[i], goombaY[i], 20, 20);
        }


        if (level == 1) {
            // Draw Mario and Goombas
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, 20, 20);  // Mario's position
        }
    }

    private void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT: left = true; break;
            case RIGHT: right = true; break;
            case UP: up = true; break;
            case DOWN: down = true; break;
        }
    }

    private void keyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT: left = false; break;
            case RIGHT: right = false; break;
            case UP: up = false; break;
            case DOWN: down = false; break;
        }
    }

    public void switchToGame() {
    }
}