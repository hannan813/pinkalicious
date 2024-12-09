package edu.sdccd.cisc190;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

// TODO: Some suggestions with the visual outcome, I think you could make your Congratulations a different popup
// TODO: Have a Congratulations Popup Screen with a background, then a button to transition to the Leaderboard popup screen
// TODO: Could also add a button to restart
    // These are just suggestions

public class CongratulationsScreen {

    public Pane createCongratsScreen(Leaderboard leaderboard) {
        Pane congratsPane = new Pane();
        congratsPane.setStyle("-fx-background-color: lightblue;");

        Text congratsText = new Text("Congratulations! You finished the game!");
        congratsText.setFont(Font.font("Arial", 30));
        congratsText.setFill(Color.DARKBLUE);
        congratsText.setTextAlignment(TextAlignment.CENTER);
        congratsText.setX(200);
        congratsText.setY(200);

        // Add leaderboard scores below the congrats text
        int yPosition = 250;
        for (String score : leaderboard.getScores()) {
            Text scoreText = new Text(score);
            scoreText.setFont(Font.font("Arial", 20));
            scoreText.setFill(Color.BLACK);
            scoreText.setTextAlignment(TextAlignment.CENTER);
            scoreText.setX(200);
            scoreText.setY(yPosition);
            congratsPane.getChildren().add(scoreText);
            yPosition += 30;
        }

        congratsPane.getChildren().add(congratsText);
        return congratsPane;
    }
}