package edu.sdccd.cisc190;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// TODO: Ensure you add Javadocs for each public method in this class.
// Examples are seen in Classes: EndOfYearProject, Enemy, & Game

public class MainMenu {
    private Stage stage;
    private Runnable startGameAction;

    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    public void display() {
        // Create a VBox layout
        VBox layout = new VBox(20); // Spacing between elements
        layout.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: lightblue;");

        // Create the Start Game button
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        startButton.setOnAction(e -> {
            if (startGameAction != null) {
                startGameAction.run(); // Run the start game action when button is clicked
            }
        });

        // Add the button to the layout
        layout.getChildren().add(startButton);

        // Create and set the scene
        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }

    public void setStartGameAction(Runnable startGameAction) {
        this.startGameAction = startGameAction;
    }

    public static Scene createMainMenu(Stage primaryStage) {
        // You can add additional logic for the main menu if needed
        return null;
    }
}

