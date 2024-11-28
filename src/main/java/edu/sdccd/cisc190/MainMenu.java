package edu.sdccd.cisc190;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

public class MainMenu {
    private Scene scene;

    public MainMenu(EndOfYearProject endOfYearProject) {
        Pane mainMenuPane = new Pane();
        Text titleText = new Text(150, 100, "The World's Hardest Game");
        titleText.setFont(new Font(40));

        Text startText = new Text(250, 200, "Press SPACE to Start");
        startText.setFont(new Font(30));

        Text quitText = new Text(250, 300, "Press Q to Quit");
        quitText.setFont(new Font(30));

        mainMenuPane.getChildren().addAll(titleText, startText, quitText);

        scene = new Scene(mainMenuPane, 800, 500);

        // Handle key presses to navigate the menu
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                // Start the game by switching to the game scene from EndOfYearProject
                endOfYearProject.switchToGame();  // Use EndOfYearProject's method to switch to the game scene
            } else if (event.getCode() == KeyCode.Q) {
                System.exit(0); // Quit the game
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
