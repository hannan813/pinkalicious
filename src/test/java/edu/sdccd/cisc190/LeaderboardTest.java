package edu.sdccd.cisc190;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    private Leaderboard leaderboard;

    @BeforeEach
    void setUp() {
        // Initialize the leaderboard with a dummy file name (the file itself won't be used)
        leaderboard = new Leaderboard("dummy.txt");

        // Reset the scores to ensure the leaderboard starts fresh for each test
        leaderboard.getScores().clear();
    }

    @Test
    void testAddScore() {
        // Add a score to the leaderboard
        leaderboard.addScore("Player1", 120, 3);

        // Retrieve the list of scores
        List<String> scores = leaderboard.getScores();

        // Check if the leaderboard contains the new score
        assertEquals(1, scores.size());
        assertTrue(scores.get(0).contains("Player1"));
        assertTrue(scores.get(0).contains("120"));
        assertTrue(scores.get(0).contains("3"));
    }

    @Test
    void testAddMultipleScores() {
        // Add multiple scores
        leaderboard.addScore("Player1", 100, 2);
        leaderboard.addScore("Player2", 150, 1);

        // Retrieve the list of scores
        List<String> scores = leaderboard.getScores();

        // Ensure both scores are in the leaderboard
        assertEquals(2, scores.size());
        assertTrue(scores.contains("Player1 - Time: 100 seconds, Fails: 2"));
        assertTrue(scores.contains("Player2 - Time: 150 seconds, Fails: 1"));
    }

    @Test
    void testGetScores() {
        // Add a score and retrieve the scores
        leaderboard.addScore("Player1", 120, 3);
        List<String> scores = leaderboard.getScores();

        // Check if the leaderboard has exactly 1 score
        assertEquals(1, scores.size());
    }

    @Test
    void testLeaderboardIsEmptyInitially() {
        // Verify that the leaderboard is empty when no scores are added
        List<String> scores = leaderboard.getScores();
        assertTrue(scores.isEmpty(), "Leaderboard should be empty initially.");
    }
}
