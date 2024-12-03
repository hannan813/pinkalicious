package edu.sdccd.cisc190;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardTest {

    private Leaderboard leaderboard;

    @BeforeEach
    public void setUp() {
        leaderboard = new Leaderboard();
    }

    @Test
    public void testAddScore() {
        leaderboard.addScore("Alice: 100");
        leaderboard.addScore("Bob: 90");

        ArrayList<String> scores = leaderboard.getScores();

        assertNotNull(scores, "Scores list should not be null");
        assertEquals(2, scores.size(), "Scores list should contain 2 entries");
        assertEquals("Alice: 100", scores.get(0), "First score should be 'Alice: 100'");
        assertEquals("Bob: 90", scores.get(1), "Second score should be 'Bob: 90'");
    }

    @Test
    public void testEmptyLeaderboard() {
        ArrayList<String> scores = leaderboard.getScores();

        assertNotNull(scores, "Scores list should not be null");
        assertTrue(scores.isEmpty(), "Scores list should be empty initially");
    }

    @Test
    public void testDisplay() {
        leaderboard.addScore("Alice: 100");
        leaderboard.addScore("Bob: 90");

        // Capture the console output (if display is using console print)
        // For GUI-based display, this test might need to interact with the GUI
        leaderboard.display();

        // Test would validate output manually or compare expected results if the method is refactored to return a display string.
        // Here, you might implement this test based on the specific display implementation.
        assertDoesNotThrow(() -> leaderboard.display(), "Display method should execute without errors");
    }
}
