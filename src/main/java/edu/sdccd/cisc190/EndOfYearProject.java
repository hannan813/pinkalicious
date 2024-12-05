package edu.sdccd.cisc190;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class EndOfYearProject extends Application {
    private Game game;  // To store the game instance

    public static void main(String[] args) {
        launch(args);  // Launch the JavaFX application
    }

    public static String getAppName() {
        return "EndOfYearProject";
    }

    @Override
    public void start(Stage primaryStage) {
        // Step 1: Initialize MainMenu with the primaryStage
        MainMenu mainMenu = new MainMenu(primaryStage);

        // Step 2: Set the action for the "Start Game" button
        mainMenu.setStartGameAction(() -> startGame(primaryStage));

        // Step 3: Display the main menu
        mainMenu.display();
    }

    // Step 4: Define the startGame method to initialize the game scene
    private void startGame(Stage primaryStage) {
        // Create a canvas for the game
        Canvas canvas = new Canvas(800, 600);  // Set the canvas size

        // Create a root group for the game scene
        Group root = new Group(canvas);

        // Create the game scene
        Scene gameScene = new Scene(root);

        // Initialize the game (You may need to pass additional parameters depending on your Game class)
        game = new Game(canvas, gameScene);

        // Set the scene to the game scene and update the title
        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Worlds Hardest Game: Mario Adititon");  // Update the window title
        primaryStage.show();  // Show the game window
    }
}