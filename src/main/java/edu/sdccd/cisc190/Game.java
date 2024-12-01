package edu.sdccd.cisc190;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Game {
    private final Player player;
    private Enemy[] enemies;  // Declare the enemies array here
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
    private int goombaSpeed = 4;  // How fast the Goombas move
    private boolean fwrd = true;  // Direction control for Goombas
    private Canvas canvas;
    private AnimationTimer gameLoop;
    private boolean isInsideGameOutline;

    public Game(Canvas canvas, Scene scene) {
        this.canvas = canvas;
        this.player = new Player(100, 350, L);
        this.enemies = new Enemy[5];
        initializeEnemies(); // Sets up goombas

        setupInput(scene);
        startGameLoop();
    }

    public void setupInput(Scene scene) {
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);
    }

    public void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(); // This calls the update method to move the player
                render(); // This calls the render method to draw everything on the screen
            }
        };
        gameLoop.start();// Starts the game loop
    }

    private boolean isInsideGameOutline(int x, int y, int width, int height) {
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
        // Check all corners of Mario
        return boundary.contains(x, y) &&                   // Top-left corner
                boundary.contains(x + width, y) &&           // Top-right corner
                boundary.contains(x, y + height) &&          // Bottom-left corner
                boundary.contains(x + width, y + height);    // Bottom-right corner
    }

    // Adding a direction array for Goombas to control movement direction (right or left)
    private boolean[] goombaDirection = new boolean[goombaX.length]; // true for right, false for left

    private void initializeEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(i); // Initialize with specific logic per Goomba
        }
    }

    private void update() {
        player.update();// This calls Player's update method to move Mario
        for (Enemy enemy : enemies) {
            enemy.update();
            if (player.checkCollision(enemy)) {
                player.respawn();
                fails++;
            }
        }
    }

    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTSKYBLUE);
        gc.fillRect(0, 0, 800, 500);

        // Draw scoreboard
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 50);

        // Set the font size and type
        gc.setFont(javafx.scene.text.Font.font("TIMES NEW ROMAN", 50));  // Adjust font size here
        gc.setFill(Color.WHITE);  // Text color

        // Draw level and fail count once
        gc.fillText("Level: " + level, 10, 40);
        gc.fillText("Fails: " + fails, 600, 40);

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

        // Render Player and Enemies
        player.render(gc);
        for (Enemy enemy : enemies) {
            enemy.render(gc);
        }
    }

    private void keyPressed(KeyEvent event) {
        player.handleKeyPressed(event);// Calls the Player method to move when a key is pressed
    }

    private void keyReleased(KeyEvent event) {
        player.handleKeyReleased(event); // Stops the player from moving when the key is released
    }
}
