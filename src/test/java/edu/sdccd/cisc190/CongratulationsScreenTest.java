package edu.sdccd.cisc190;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CongratulationsScreenTest {

    private CongratulationsScreen congratsScreen;
    private Leaderboard leaderboard;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file to simulate file I/O operations
        File tempFile = Files.createTempFile("leaderboard", ".txt").toFile();
        tempFile.deleteOnExit(); // Ensures the temp file is deleted when the JVM exits

        // Create an instance of Leaderboard with the temporary file
        leaderboard = new Leaderboard(tempFile.getAbsolutePath());

        // Add some sample scores to the leaderboard
        leaderboard.addScore("Player1: 1000");
        leaderboard.addScore("Player2: 900");

        // Create an instance of CongratulationsScreen
        congratsScreen = new CongratulationsScreen();
    }

    @Test
    void testCreateCongratsScreen() {
        // Create the congratulatory screen using the leaderboard
        Pane congratsPane = congratsScreen.createCongratsScreen(leaderboard);

        // Check that the Pane is not null
        assertNotNull(congratsPane, "The congrats pane should not be null.");

        // Check if the congratulatory message is in the pane
        boolean foundCongratsMessage = false;
        for (int i = 0; i < congratsPane.getChildren().size(); i++) {
            if (congratsPane.getChildren().get(i) instanceof Text) {
                Text textNode = (Text) congratsPane.getChildren().get(i);
                if (textNode.getText().equals("Congratulations! You finished the game!")) {
                    foundCongratsMessage = true;
                    break;
                }
            }
        }
        assertTrue(foundCongratsMessage, "The congratulatory message should be in the pane.");

        // Check if the leaderboard scores are in the pane
        boolean foundPlayer1Score = false;
        boolean foundPlayer2Score = false;
        for (int i = 0; i < congratsPane.getChildren().size(); i++) {
            if (congratsPane.getChildren().get(i) instanceof Text) {
                Text textNode = (Text) congratsPane.getChildren().get(i);
                if (textNode.getText().equals("Player1: 1000")) {
                    foundPlayer1Score = true;
                }
                if (textNode.getText().equals("Player2: 900")) {
                    foundPlayer2Score = true;
                }
            }
        }

        assertTrue(foundPlayer1Score, "Player1's score should be displayed in the pane.");
        assertTrue(foundPlayer2Score, "Player2's score should be displayed in the pane.");
    }
}
