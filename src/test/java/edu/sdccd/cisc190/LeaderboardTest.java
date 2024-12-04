package edu.sdccd.cisc190;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardTest {

    @Test
    public void testEmptyLeaderboard() {
        Leaderboard leaderboard = new Leaderboard();
        // Test that the leaderboard is initialized with no scores
        assertNotNull(leaderboard.getScores());  // The scores list should not be null
        assertTrue(leaderboard.getScores().isEmpty());  // The list should be empty initially
    }

    @Test
    public void testAddScore() {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore("100");
        leaderboard.addScore("200");

        // Test that the scores are added correctly
        assertEquals(2, leaderboard.getScores().size());  // The list should have 2 scores
        assertTrue(leaderboard.getScores().contains("100"));
        assertTrue(leaderboard.getScores().contains("200"));
    }
}