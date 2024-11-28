package edu.sdccd.cisc190;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Leaderboard {

    private Scene scene;

    public Leaderboard() {
        Pane leaderboardPane = new Pane();
        Text leaderboardText = new Text(150, 100, "Leaderboard");
        leaderboardText.setFont(new Font(30));

        // Display leaderboard data here (for now, static text)
        Text player1Score = new Text(150, 150, "Player1: 5000");
        player1Score.setFont(new Font(20));

        leaderboardPane.getChildren().addAll(leaderboardText, player1Score);

        scene = new Scene(leaderboardPane, 800, 500);
    }

    public Scene getScene() {
        return scene;
    }
}
