package edu.sdccd.cisc190;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Leaderboard {

    public Scene createLeaderboardScene() {
        Pane leaderboardPane = new Pane();
        Text leaderboardText = new Text(100, 100, "Leaderboard");
        leaderboardText.setStyle("-fx-font-size: 30px;");

        // Display some sample scores (you can add real score handling later)
        Text scoreText = new Text(100, 150, "1. Player1 - 100");
        Text scoreText2 = new Text(100, 200, "2. Player2 - 80");
        leaderboardPane.getChildren().addAll(leaderboardText, scoreText, scoreText2);

        return new Scene(leaderboardPane, 800, 500);
    }
}
