package edu.sdccd.cisc190;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

    public class Game {

        private int x = 100, y = 350;
        private int level = 1;
        private boolean gameOn = true, left = false, up = false, down = false, right = false;

        private Canvas canvas = new Canvas(800, 500);

        public Scene createGameScene(Stage stage) {
            Pane root = new Pane();
            root.getChildren().add(canvas);

            Scene scene = new Scene(root, 800, 500);
            stage.setTitle("End of Year Project");
            stage.setScene(scene);

            scene.setOnKeyPressed(this::keyPressed);
            scene.setOnKeyReleased(this::keyReleased);

            startGameLoop();
            return scene;
        }

        public void startGameLoop() {
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (gameOn) {
                        updateGame();
                        drawGame();
                    }
                }
            }.start();
        }

        private void updateGame() {
            if (level == 1) {
                // Level 1 Logic
            } else if (level == 2) {
                // Level 2 Logic
            } else if (level == 3) {
                // Level 3 Logic
                gameOn = false; // End game after level 3
                showCongratulationsScreen();
            }
        }

        private void showCongratulationsScreen() {
            // Transition to the Congratulations screen after level 3
            CongratulationsScreen congratulationsScreen = new CongratulationsScreen();
            Pane congratsPane = congratulationsScreen.createCongratsScreen();
            Scene congratsScene = new Scene(congratsPane, 800, 500);
            Stage currentStage = (Stage) canvas.getScene().getWindow();
            currentStage.setScene(congratsScene);
        }

        private void drawGame() {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(0, 0, 800, 500);

            // Level Drawing logic
            if (level == 1) {
                // Draw level 1 elements
            } else if (level == 2) {
                // Draw level 2 elements
            }
        }

        private void keyPressed(KeyEvent event) {
            KeyCode code = event.getCode();
            switch (code) {
                case LEFT -> left = true;
                case RIGHT -> right = true;
                case UP -> up = true;
                case DOWN -> down = true;
            }
        }

        private void keyReleased(KeyEvent event) {
            KeyCode code = event.getCode();
            switch (code) {
                case LEFT -> left = false;
                case RIGHT -> right = false;
                case UP -> up = false;
                case DOWN -> down = false;
            }
        }
    }


