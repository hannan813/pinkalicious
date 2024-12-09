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
    private Leaderboard leaderboard;
    private CongratulationsScreen congratulationsScreen;

    /**
     * Manages the player, enemies, and game logic.
     */
    public Game(Canvas canvas, Scene scene, Stage stage) {
        this.canvas = canvas;
        this.enemies = new Enemy[5];
        this.player = new Player(100, 350, L, this);
        this.level = new Level(); // Initialize the Level class
        this.stage = stage; // Assign the stage
        this.congratulationsScreen = new CongratulationsScreen(); // Initialize the congratulations screen
        this.leaderboard = new Leaderboard("leaderboard.txt"); // File to store scores
        initializeEnemies();
        initializeGameOutline();
        long startTime = System.currentTimeMillis();  // Start the timer


        // TODO: I think that you could implement the timer throughout your entire game so that players could track how long it took them to complete the game overall
            // You can still show the result at the end in the leaderboard, but put a timer throughout the entire game
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
                        long endTime = System.currentTimeMillis();
                        long timeTaken = (endTime - startTime) / 1000; // Time in seconds
                        leaderboard.addScore("Player", (int) timeTaken, fails);
                        System.out.println("Congratulations! You've completed the game!");
                        showCongratulationsScreen(); // Display the congratulations screen
                    } else {
                        update();
                        render();
                    }
                    lastUpdate = now;
                }
            }

            /**
             * Shows congratulations screen and leaderboard when the game done.
             */
            private void showCongratulationsScreen() {
                // Transition to the blank level for the congratulations screen
                level.loadCongratulationsLevel(canvas.getGraphicsContext2D());

                // Show the congratulations screen and leaderboard
                Pane congratsPane = congratulationsScreen.createCongratsScreen(leaderboard);
                Scene congratsScene = new Scene(congratsPane, 800, 600); // Adjust dimensions if needed
                stage.setScene(congratsScene);
                stage.show(); // Show the updated stage
            }
        };
        gameLoop.start();
    }

    /**
     * Returns the current speed of the game.
     */
    public int getSpeed() {
        return SPEED;
    }

    /**
     * Initializes the enemies in game.
     * Sets up the enemy objects with their positions and behavior.
     */
    private void initializeEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(i, 350, this);
        }
    }

    /**
     * Game outline to show level layout and include boundaries
     * Creates a polygon to show the game’s outline.
     */
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

    /**
     * Checks if an area (x, y, width, height) is within the boundary of game.
     *
     * @param x The x-coordinate of the area.
     * @param y The y-coordinate of the area.
     * @param width The width of the area.
     * @param height The height of the area.
     * @return true if the area is inside the game boundary; false otherwise.
     */
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

    /**
     * Sets up the key press and release event handlers for the game.
     *
     * @param scene The scene where the input events will be handled.
     */
    private void setupInput(Scene scene) {
        scene.setOnKeyPressed(event -> player.handleKeyPressed(event));
        scene.setOnKeyReleased(event -> player.handleKeyReleased(event));
    }

    /**
     * Updates the game state, including the player's and enemies' positions, and checks for collisions.
     * If the player collides with an enemy, the player is respawned and the fail count is incremented.
     */
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

    /**
     * Renders the game scene, including the player, enemies, static elements, and the scoreboard.
     */
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

    /**
     * Renders the static elements of the game, such as the green tubes and the grid.
     * This method is responsible for rendering elements that do not change dynamically.
     *
     * @param gc The graphics context to draw the static elements.
     */
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

    /**
     * Determines whether a specific grid cell should be skipped when rendering.
     *
     * @param i The row index of the grid.
     * @param j The column index of the grid.
     * @return true if the grid cell should be skipped; false otherwise.
     */
    private boolean shouldSkipGridCell(int i, int j) {
        return (j == 0 && i <= 5) ||
                (j == GRID_COLS - 1 && i >= 1) ||
                (i == 0 && j < GRID_COLS - 2) ||
                (i == GRID_ROWS - 1 && j >= 2);
    }

    /**
     * Checks if the game is over, which occurs when the current level exceeds 6.
     *
     * @return true if the game is over; false otherwise.
     */
    public boolean isGameOver() {
        return level.getCurrentLevel() > 6; // Game ends after level 6
    }
}