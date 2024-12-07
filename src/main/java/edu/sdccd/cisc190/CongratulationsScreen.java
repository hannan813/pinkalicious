package edu.sdccd.cisc190;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CongratulationsScreen {

    public Pane createCongratsScreen() {
        Pane congratsPane = new Pane();
        congratsPane.setStyle("-fx-background-color: lightblue;"); // Add a background color

        Text congratsText = new Text("Congratulations! You finished the game!");
        congratsText.setFont(Font.font("Arial", 30));
        congratsText.setFill(Color.DARKBLUE); // Set text color
        congratsText.setTextAlignment(TextAlignment.CENTER);

        // Center the text in the Pane
        congratsText.setX(200); // Adjust based on your window size
        congratsText.setY(200);

        congratsPane.getChildren().add(congratsText);
        return congratsPane;
    }
}