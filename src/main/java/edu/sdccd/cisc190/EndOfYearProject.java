package edu.sdccd.cisc190;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class EndOfYearProject extends Application {
    private Game game;

    public static void main(String[] args) {
        int m = 100;  // Example starting coordinates
        int n = 50;
        int L = 20;  // Example scale factor
        Player player = new Player(m, n, L);  // Pass m, n, and L to the Player constructor
        // Now the player object can use m, n, and L
        launch(args);
    }

    public static String getAppName() {
        return "EndOfYearProject";
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 500); // Create a canvas for rendering the game
        Group root = new Group(canvas); // Add the canvas to a root group
        Scene scene = new Scene(root); // Create a scene with the root group


        game = new Game(canvas,scene); // Initialize the game
        game.setupInput(scene);  // Set up input handlers
        game.startGameLoop();   // Start the game loop

        primaryStage.setTitle("End of Year Project"); // Set the title of the window
        primaryStage.setScene(scene); // Set the scene for the stage (window)
        primaryStage.show(); // Display the stage (window)
    }
}
