package edu.sdccd.cisc190;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

// TODO: I already did this part for you, but I added a Javadocs here because I did not know this was your Main Class since it was a different title
// TODO: Be sure to add Javadocs throughout your code for public methods in each of your classes
    // Except for Coin Class because that class is not needed in your code
    // Javadocs allows people to understand the main idea happening behind each public method

/**
 * Main class for the game application. Starts game and handles main menu.
 */
public class EndOfYearProject extends Application {
    private Game game;  // To store the game instance

    /**
     * Method to launch the game.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);  // Launch the JavaFX application
    }

    /**
     * Get the name of the application.
     *
     * @return The name of the application.
     */
    public static String getAppName() {
        return "EndOfYearProject";
    }

    /**
     * Initializes the main menu and sets the action for starting the game.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Step 1: Initialize MainMenu with the primaryStage
        MainMenu mainMenu = new MainMenu(primaryStage);

        // Step 2: Set the action for the "Start Game" button
        mainMenu.setStartGameAction(() -> startGame(primaryStage));

        // Step 3: Display the main menu
        mainMenu.display();
    }

    /**
     * Starts the game by setting up the game scene and starting game components.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    // Step 4: Define the startGame method to initialize the game scene
    private void startGame(Stage primaryStage) {
        // Create a canvas for the game
        Canvas canvas = new Canvas(800, 600);  // Set the canvas size

        // Create a root group for the game scene
        Group root = new Group(canvas);

        // Creates the game scene
        Scene gameScene = new Scene(root);

        // Initialize the game (You may need to pass additional parameters depending on your Game class)
        game = new Game(canvas, gameScene, primaryStage);

        // Set the scene to the game scene and update the title
        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Worlds Hardest Game: Mario Adititon");  // Update the window title
        primaryStage.show();  // Show the game window
    }
}