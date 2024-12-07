package edu.sdccd.cisc190;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    private Leaderboard leaderboard;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file to simulate file I/O operations
        tempFile = Files.createTempFile("leaderboard", ".txt").toFile();
        tempFile.deleteOnExit(); // Ensures the temp file is deleted when the JVM exits

        // Create an instance of Leaderboard with the temporary file
        leaderboard = new Leaderboard(tempFile.getAbsolutePath());

        // Add some initial scores to the leaderboard
        leaderboard.addScore("Player1: 1000");
        leaderboard.addScore("Player2: 900");
    }

    @Test
    void testGetScores() {
        List<String> scores = leaderboard.getScores();

        // Verify the leaderboard contains the expected scores
        assertNotNull(scores);
        assertEquals(2, scores.size());
        assertTrue(scores.contains("Player1: 1000"));
        assertTrue(scores.contains("Player2: 900"));
    }

    @Test
    void testAddScore() {
        leaderboard.addScore("Player3: 800");

        List<String> scores = leaderboard.getScores();

        // Verify the score was added
        assertEquals(3, scores.size());
        assertTrue(scores.contains("Player3: 800"));
    }

    @Test
    void testSaveScores() throws IOException {
        // Save the scores and then load them again to verify the file content
        leaderboard.addScore("Player4: 700");
        leaderboard.addScore("Player5: 600");

        // Recreate leaderboard object to simulate a fresh load from file
        Leaderboard newLeaderboard = new Leaderboard(tempFile.getAbsolutePath());

        List<String> scores = newLeaderboard.getScores();

        // Verify that the new leaderboard contains all the saved scores
        assertTrue(scores.contains("Player4: 700"));
        assertTrue(scores.contains("Player5: 600"));
    }

    @Test
    void testDisplay() {
        // Simply test that the display method works without exceptions
        leaderboard.display();
    }
}
