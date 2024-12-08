package edu.sdccd.cisc190;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LevelTest {


    @Test
    public void testInitialLevel() {
        Level level = new Level();
        assertEquals(1, level.getCurrentLevel(), "Initial level should be 1.");
    }


    @Test
    public void testNextLevel() {
        Level level = new Level();
        level.nextLevel();
        assertEquals(2, level.getCurrentLevel(), "After one call to nextLevel, the level should be 2.");
    }


    @Test
    public void testMaxLevel() {
        Level level = new Level();
        for (int i = 0; i < 10; i++) {
            level.nextLevel();
        }
        assertEquals(7, level.getCurrentLevel(), "Level should not exceed the maximum level of 6.");
    }
}
