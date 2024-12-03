package edu.sdccd.cisc190;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Game extends Application {

    private final Player player;
    private Enemy[] enemies;
    private int x = 100, y = 350;
    private int level = 1, fails = 0, coins = 0;
    private boolean left = false, up = false, down = false, right = false;
    private boolean repeat = true;
    private int m = 72, n = 115, L = 40;
    private Canvas canvas;
    private AnimationTimer gameLoop;

    public Game() {
        this.player = new Player(100, 350, L);
        this.enemies = new Enemy[5];
        initializeEnemies(); // Sets up enemies
    }

    public static void main(String[] args) {
        launch(args);  // Launch the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(800, 500);
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        setupInput(scene);
        startGameLoop();

        primaryStage.setTitle("JavaFX Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setupInput(Scene scene) {
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);
    }

    public void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();  // Calls the update method
                render();  // Calls the render method
            }
        };
        gameLoop.start();  // Starts the game loop
    }

    private void initializeEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(i); // Initialize each enemy
        }
    }

    private void update() {
        player.update();  // Update player position
        for (Enemy enemy : enemies) {
            enemy.update();  // Update enemy position
            if (player.checkCollision(enemy)) {
                player.respawn();  // Respawn player on collision
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
        gc.setFont(javafx.scene.text.Font.font("TIMES NEW ROMAN", 50));  // Font for score
        gc.setFill(Color.WHITE);
        gc.fillText("Level: " + level, 10, 40);
        gc.fillText("Fails: " + fails, 600, 40);

        // Create a Polygon for the border
        Polygon border = new Polygon();
        border.getPoints().addAll(
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
        border.setStroke(Color.BLACK);
        border.setFill(Color.TRANSPARENT);
        border.setStrokeWidth(1);
        Group root = (Group) canvas.getParent();
        root.getChildren().add(border);

        // Draw green tubes
        Color tubeColor = Color.color(144 / 255f, 238 / 255f, 144 / 255f);
        gc.setFill(tubeColor);
        gc.fillRect(m, n, 120, 280);  // First tube
        gc.fillRect(m + 520, n, 120, 280);  // Second tube

        // Draw grid
        Color gridColor = Color.color(196 / 255f, 164 / 255f, 132 / 255f);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                int xPos = m + 120 + (j * 40);
                int yPos = n + (i * 40);
                if (j == 0 && (i >= 0 && i <= 5)) {
                    continue;
                }
                if (j == 9 && (i >= 1 && i <= 6)) {
                    continue;
                }
                if ((i == 0 && j < 8) || (i == 6 && j >= 2)) {
                    continue;
                }
                if ((i + j) % 2 == 0) {
                    gc.setFill(gridColor);
                } else {
                    gc.setFill(Color.WHITE);
                }
                gc.fillRect(xPos, yPos, 40, 40);
            }
        }

        // Render player and enemies
        player.render(gc);
        for (Enemy enemy : enemies) {
            enemy.render(gc);
        }
    }

    private void keyPressed(KeyEvent event) {
        player.handleKeyPressed(event);
    }

    private void keyReleased(KeyEvent event) {
        player.handleKeyReleased(event);
    }

    // Player class
    class Player {
        private int x, y, size;

        public Player(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public void update() {
            // Update player movement logic
        }

        public void render(GraphicsContext gc) {
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, size, size);
        }

        public boolean checkCollision(Enemy enemy) {
            Bounds playerBounds = new Bounds(x, y, size, size);
            return playerBounds.intersects(enemy.getX(), enemy.getY(), 40, 40);
        }

        public void respawn() {
            // Logic to respawn player
            x = 100;
            y = 350;
        }

        public void handleKeyPressed(KeyEvent event) {
            // Handle key press to move player
        }

        public void handleKeyReleased(KeyEvent event) {
            // Stop movement when key is released
        }
    }

    // Enemy class
    class Enemy {
        private int x, y;
        private int speed;
        private boolean directionRight;

        public Enemy(int index) {
            this.x = 100 + (index * 100);
            this.y = 350;
            this.speed = 2;
            this.directionRight = true;
        }

        public void update() {
            if (directionRight) {
                x += speed;
                if (x >= 600) {  // Change direction when hitting screen boundary
                    directionRight = false;
                }
            } else {
                x -= speed;
                if (x <= 100) {
                    directionRight = true;
                }
            }
        }

        public void render(GraphicsContext gc) {
            gc.setFill(Color.RED);
            gc.fillRect(x, y, 40, 40);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
