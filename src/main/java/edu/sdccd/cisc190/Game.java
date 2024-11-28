package edu.sdccd.cisc190;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Game {

    private Pane gamePane;
    private Scene scene;
    private Player player;
    private Enemy[] enemies;
    private Rectangle[] obstacles;
    private Coin[] coins;
    private Text levelText;
    private Text scoreText;
    private int currentLevel;
    private int score;
    private boolean levelComplete;

    public Game() {
        gamePane = new Pane();
        currentLevel = 1;
        score = 0;
        levelComplete = false;

        player = new Player(100, 100);
        enemies = new Enemy[5];  // Adjust number of enemies
        obstacles = new Rectangle[5]; // Obstacles
        coins = new Coin[3]; // Coins for levels 2 and 3

        levelText = new Text(10, 20, "Level: " + currentLevel);
        levelText.setFont(new Font(20));

        scoreText = new Text(700, 20, "Score: " + score);
        scoreText.setFont(new Font(20));

        gamePane.getChildren().addAll(levelText, scoreText);
        setupLevel();

        scene = new Scene(gamePane, 800, 600);
        scene.setFill(Color.CYAN);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                player.moveRight();
            } else if (event.getCode() == KeyCode.LEFT) {
                player.moveLeft();
            } else if (event.getCode() == KeyCode.UP) {
                player.moveUp();
            } else if (event.getCode() == KeyCode.DOWN) {
                player.moveDown();
            }
        });

        // Game loop for enemy movement and checking win/lose
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!levelComplete) {
                    updateGame();
                    // Update enemy movement
                    for (Enemy enemy : enemies) {
                        if (enemy != null) {
                            enemy.updateMovement();  // You need to define this method in the Enemy class
                        }
                    }
                }
            }
        }.start();
    }

    private void setupLevel() {
        // Clear previous level's objects
        gamePane.getChildren().clear();
        gamePane.getChildren().addAll(levelText, scoreText);

        // Reset arrays based on the level
        if (currentLevel == 1) {
            enemies = new Enemy[3];
            obstacles = new Rectangle[2];
            coins = new Coin[0];
        } else if (currentLevel == 2) {
            enemies = new Enemy[5];
            obstacles = new Rectangle[3];
            coins = new Coin[2];
        } else if (currentLevel == 3) {
            enemies = new Enemy[7];
            obstacles = new Rectangle[5];
            coins = new Coin[5];
        }

        // Draw grid, obstacles, enemies, and coins
        drawGrid();
        addGreenTubes();
        addEnemies();
        addCoins();
    }

    private void drawGrid() {
        for (int i = 0; i < 800; i += 40) {
            Rectangle verticalLine = new Rectangle(i, 0, 1, 600);
            verticalLine.setFill(Color.BLACK);
            gamePane.getChildren().add(verticalLine);
        }
        for (int i = 0; i < 600; i += 40) {
            Rectangle horizontalLine = new Rectangle(0, i, 800, 1);
            horizontalLine.setFill(Color.BLACK);
            gamePane.getChildren().add(horizontalLine);
        }
    }

    private void addGreenTubes() {
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Rectangle(150 + i * 100, 150 + i * 50, 40, 40);
            obstacles[i].setFill(Color.GREEN);
            gamePane.getChildren().add(obstacles[i]);
        }
    }

    private void addEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null) {
                enemies[i] = new Enemy(100 + i * 100, 100);
            }
            gamePane.getChildren().add(enemies[i].getShape());
        }
    }

    private void addCoins() {
        for (int i = 0; i < coins.length; i++) {
            coins[i] = new Coin(100 + i * 120, 200 + i * 50);
            gamePane.getChildren().add(coins[i].getShape());
        }
    }

    private void updateGame() {
        for (Rectangle obstacle : obstacles) {
            if (obstacle != null && player.getShape().getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                player.resetPosition();
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy != null && player.getShape().getBoundsInParent().intersects(enemy.getShape().getBoundsInParent())) {
                player.resetPosition();
            }
        }

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] != null && player.getShape().getBoundsInParent().intersects(coins[i].getShape().getBoundsInParent())) {
                score += 10;
                gamePane.getChildren().remove(coins[i].getShape());
                coins[i] = null;
            }
        }

        if (player.getShape().getLayoutX() >= 750) {
            levelComplete = true;
            showLevelComplete();
        }

        scoreText.setText("Score: " + score);
    }

    private void showLevelComplete() {
        Text levelCompleteText = new Text(300, 250, "Level " + currentLevel + " Complete!");
        levelCompleteText.setFont(new Font(30));
        gamePane.getChildren().add(levelCompleteText);

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (currentLevel < 3) {
                currentLevel++;
                levelText.setText("Level: " + currentLevel);
                gamePane.getChildren().remove(levelCompleteText);
                setupLevel();
                levelComplete = false;
            }
        }).start();
    }

    public Scene getScene() {
        return scene;
    }
}
