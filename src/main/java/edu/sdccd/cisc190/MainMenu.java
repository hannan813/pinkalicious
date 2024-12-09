package edu.sdccd.cisc190;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        
        // Button state management: Disable the button after it is clicked
        startButton.setOnAction(e -> {
            if (startGameAction != null) {
                startButton.setDisable(true); // Disable the button to prevent multiple triggers
                startGameAction.run(); // Run the start game action when button is clicked
            }
        });

        // TODO: Consider adding an event filter or listener to ensure the button isn't triggered multiple times accidentally.
        startButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            if (startButton.isDisabled()) {
                event.consume(); // Consume the event to prevent further processing if the button is disabled
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

    // TODO: Implement additional logic or remove this unused method if not needed.
    public static Scene createMainMenu(Stage primaryStage) {
        // You can add additional logic for the main menu if needed
        return null;
    }
}
