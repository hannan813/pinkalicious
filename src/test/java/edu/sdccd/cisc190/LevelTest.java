package edu.sdccd.cisc190;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {

    private Level level;

    @BeforeEach
    public void setUp() {
        level = new Level();
    }

    @Test
    public void testInitialLevel() {
        assertEquals(1, level.getCurrentLevel(), "Initial level should be 1");
    }

    @Test
    public void testNextLevel() {
        level.nextLevel();
        assertEquals(2, level.getCurrentLevel(), "Level should increment to 2 after calling nextLevel");

        level.nextLevel();
        assertEquals(3, level.getCurrentLevel(), "Level should increment to 3 after calling nextLevel twice");
    }

    @Test
    public void testMultipleLevelAdvancements() {
        for (int i = 0; i < 10; i++) {
            level.nextLevel();
        }
        assertEquals(11, level.getCurrentLevel(), "Level should correctly increment after multiple nextLevel calls");
    }
}
