package edu.sdccd.cisc190;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

    public class MainMenu {

        public Scene createMainMenuScene(Stage stage) {
            Pane mainMenuPane = new Pane();
            Text title = new Text(300, 100, "End of Year Project");
            title.setStyle("-fx-font-size: 30px;");

            Button startButton = new Button("Start Game");
            startButton.setLayoutX(350);
            startButton.setLayoutY(200);
            startButton.setOnAction(e -> startGame(stage));

            Button leaderboardButton = new Button("Leaderboard");
            leaderboardButton.setLayoutX(350);
            leaderboardButton.setLayoutY(250);
            leaderboardButton.setOnAction(e -> showLeaderboard(stage));

            mainMenuPane.getChildren().addAll(title, startButton, leaderboardButton);

            return new Scene(mainMenuPane, 800, 500);
        }

        private <Game> void startGame(Stage stage) {
            Game game = new Game();
            Scene gameScene = game.getClass();
            stage.setScene(gameScene);
        }

        private void showLeaderboard(Stage stage) {
            Leaderboard leaderboard = new Leaderboard();
            Scene leaderboardScene = leaderboard.createLeaderboardScene();
            stage.setScene(leaderboardScene);
        }
    }



