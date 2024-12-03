package edu.sdccd.cisc190;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class EnemyTest extends Application {

    private Enemy enemy;

    @Override
    public void start(Stage primaryStage) {
        // Create a Canvas to render the game on
        Canvas canvas = new Canvas(800, 600);
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 800, 600);

        // Create an instance of the Enemy (Goomba)
        enemy = new Enemy(0); // Starting position for first enemy

        // Get the GraphicsContext to render on the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set up the main window
        primaryStage.setTitle("Enemy Test");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the game loop to update and render the enemy
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);  // Slow down the updates to make it visible
                    enemy.update();  // Update enemy position
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  // Clear the canvas
                    enemy.render(gc);  // Render the updated enemy

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
