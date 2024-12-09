package edu.sdccd.cisc190;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CongratulationsScreen {

    public Pane createCongratsScreen(Leaderboard leaderboard) {
        // TODO: Add null or empty checks for leaderboard to avoid potential NullPointerExceptions
        if (leaderboard == null || leaderboard.getScores() == null || leaderboard.getScores().isEmpty()) {
            return new Pane(); // Return an empty pane if the leaderboard is invalid or empty
        }

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
