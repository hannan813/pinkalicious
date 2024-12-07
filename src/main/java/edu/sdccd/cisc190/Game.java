package edu.sdccd.cisc190;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player player;
    private Enemy[] enemies;
    private int fails = 0;
    private int score = 0;
    private List<Coin> coins = new ArrayList<>();
    private boolean repeat = true;
    private final int m = 72, n = 115, L = 40;
    private final Canvas canvas;
    private final AnimationTimer gameLoop;
    private Polygon gameOutline;
    private static final int GRID_ROWS = 7;
    private static final int GRID_COLS = 10;
    private int SPEED = 2;
    private final Level level; // Integrate Level class
    private Stage stage;
    private CongratulationsScreen congratulationsScreen;

    public Game(Canvas canvas, Scene scene, Stage stage) {
        this.canvas = canvas;
        this.enemies = new Enemy[5];
        this.player = new Player(100, 350, L, this);
        this.level = new Level(); // Initialize the Level class
        this.stage = stage; // Assign the stage
        this.congratulationsScreen = new CongratulationsScreen(); // Initialize the congratulations screen
        initializeEnemies();
        initializeGameOutline();

        setupInput(scene);
        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            private static final long FPS = 60;
            private static final long NANO_FPS = 1_000_000_000 / FPS;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= NANO_FPS) {
                    if (isGameOver()) {
                        gameLoop.stop(); // Stop the game loop if game is over
                        System.out.println("Congratulations! You've completed the game!");
                        showCongratulationsScreen(); // Display the congratulations screen
                    } else {
                        update();
                        render();
                    }
                    lastUpdate = now;
                }
            }

            private void showCongratulationsScreen() {
                Pane congratsPane = congratulationsScreen.createCongratsScreen();
                Scene congratsScene = new Scene(congratsPane, 800, 600); // Adjust dimensions if needed
                stage.setScene(congratsScene);
                stage.show(); // Show the updated stage
            }
        };
        gameLoop.start();
    }

    public int getSpeed() {
        return SPEED;
    }

    private void initializeEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(i, this);
        }
    }

    private void initializeGameOutline() {
        gameOutline = new Polygon(
                m, n,
                m + 3 * L, n,
                m + 3 * L, n + 6 * L,
                m + 4 * L, n + 6 * L,
                m + 4 * L, n + L,
                m + 11 * L, n + L,
                m + 11 * L, n,
                m + 16 * L, n,
                m + 16 * L, n + 7 * L,
                m + 13 * L, n + 7 * L,
                m + 13 * L, n + L,
                m + 12 * L, n + L,
                m + 12 * L, n + 6 * L,
                m + 5 * L, n + 6 * L,
                m + 5 * L, n + 7 * L,
                m, n + 7 * L
        );
    }

    public boolean isInsideGameOutline(int x, int y, int width, int height) {
        if (x >= 600) {
            SPEED++;
            level.nextLevel(); // Advance to the next level
            if (!isGameOver()) {
                player.respawn();
            }
            return false;
        }

        return gameOutline.contains(x, y) &&
                gameOutline.contains(x + width, y) &&
                gameOutline.contains(x, y + height) &&
                gameOutline.contains(x + width, y + height);
    }

    private void setupInput(Scene scene) {
        scene.setOnKeyPressed(event -> player.handleKeyPressed(event));
        scene.setOnKeyReleased(event -> player.handleKeyReleased(event));
    }

    private void update() {
        player.update();
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
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Background
        gc.setFill(Color.LIGHTSKYBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Scoreboard
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), 50);
        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font("TIMES NEW ROMAN", 50));
        gc.fillText("Level: " + level.getCurrentLevel(), 10, 40); // Show current level
        gc.fillText("Fails: " + fails, canvas.getWidth() - 200, 40);

        // Static elements
        renderStaticElements(gc);

        // Render player and enemies
        player.render(gc);
        for (Enemy enemy : enemies) {
            enemy.render(gc);
        }
    }

    private void renderStaticElements(GraphicsContext gc) {
        // Green tubes
        gc.setFill(Color.LIMEGREEN);
        gc.fillRect(m, n, 120, 280);
        gc.fillRect(m + 520, n, 120, 280);

        // Grid
        Color brown = Color.color(0.77, 0.64, 0.52);
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLS; j++) {
                int xPos = m + 120 + (j * 40);
                int yPos = n + (i * 40);

                if (shouldSkipGridCell(i, j)) continue;

                gc.setFill((i + j) % 2 == 0 ? brown : Color.WHITE);
                gc.fillRect(xPos, yPos, 40, 40);
            }
        }
    }

    private boolean shouldSkipGridCell(int i, int j) {
        return (j == 0 && i <= 5) ||
                (j == GRID_COLS - 1 && i >= 1) ||
                (i == 0 && j < GRID_COLS - 2) ||
                (i == GRID_ROWS - 1 && j >= 2);
    }

    public boolean isGameOver() {
        return level.getCurrentLevel() > 6; // Game ends after level 6
    }
}