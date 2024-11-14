package edu.sdccd.cisc190;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class EndOfYearProject extends Application {
    private int x = 100, y = 350;
    private int a1 = 512, a2 = 252, av1 = 120, av2 = 660, b = 171, bv = 190;
    private boolean gameOn = true, left = false, up = false, down = false, right = false;
    private int fails = 0, level = 1, coins = 0;
    private boolean coin1held = false, coin1stored = false, coin2held = false, coin2stored = false;
    private boolean upArea = true, repeat = true, fwrd = true;

    private Canvas canvas = new Canvas(800, 500);
    private int[] xPoints = {72, 192, 192, 232, 232, 392, 392, 632, 632, 512, 512, 472, 472, 192, 192, 72};
    private int[] yPoints = {115, 115, 355, 355, 155, 155, 115, 115, 435, 435, 155, 155, 355, 355, 435, 435};
    private int numPoints = xPoints.length;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("End Of Year Project");
        primaryStage.show();

        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);

        // Game loop using AnimationTimer
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
        int c = 2, r = 3;

        if (level == 1) {
            if (repeat) {
                fwrd = !(a2 > 512);
            }
            if (fwrd) {
                a2 += r;
                a1 -= r;
            } else {
                a2 -= r;
                a1 += r;
                repeat = a2 < 252;
            }
            if (right) x += c;
            if (left) x -= c;
            if (up) y -= c;
            if (down) y += c;
        }
    }

    private void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Background
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, 800, 500);

        // Scoreboard
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Times New Roman", 24));
        gc.fillText("Level: " + level, 10, 30);
        gc.fillText("Fails: " + fails, 700, 30);

        // Draw Level 1
        if (level == 1) {
            gc.setFill(Color.BLACK);
            gc.strokePolygon(
                    new double[]{72, 192, 192, 232, 232, 392, 392, 632, 632, 512, 512, 472, 472, 192, 192, 72},
                    new double[]{115, 115, 355, 355, 155, 155, 115, 115, 435, 435, 155, 155, 355, 355, 435, 435},
                    numPoints);

            // Player (Mario) position
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, 20, 20);

            // Goombas (Enemies)
            gc.setFill(Color.BLACK);
            gc.fillOval(a1, b, 20, 20);
            gc.fillOval(a2, b + 36, 20, 20);
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

    public static void main(String[] args) {
        launch(args);
    }
}