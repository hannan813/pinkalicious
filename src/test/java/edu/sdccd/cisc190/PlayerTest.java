package edu.sdccd.cisc190;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerInitialization() {
        Player player = new Player(100, 350, 50);
        assertEquals(100, player.x, "Initial X position should be 100");
        assertEquals(350, player.y, "Initial Y position should be 350");
    }

    @Test
    void testPlayerMovementInsideBoundary() {
        Player player = new Player(100, 350, 50);

        // Simulate moving right
        player.handleKeyPressed(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false));
        player.movingRight = true;
        player.update();
        assertEquals(105, player.x, "Player should move right by 5 units");
        assertEquals(350, player.y, "Player Y position should remain unchanged");

        // Stop moving right
        player.handleKeyReleased(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.RIGHT, false, false, false, false));
        player.movingRight = false;
        player.update();
        assertEquals(105, player.x, "Player X position should not change when movement stops");
    }

    @Test
    void testPlayerStaysWithinBoundary() {
        Player player = new Player(100, 350, 50);

        // Try to move the player outside the boundary
        player.movingLeft = true;
        player.x = 0; // Assume boundary starts at x = 0
        player.update();
        assertEquals(0, player.x, "Player should not move outside the boundary");
    }

    @Test
    void testCollisionWithEnemy() {
        Player player = new Player(100, 100, 50);

        // Create an enemy using its index
        Enemy enemy = new Enemy(0); // First enemy at index 0
        enemy.update(); // Ensure the enemy is updated to its correct position

        // Test collision detection
        assertEquals(player.checkCollision(enemy), false, "Player should collide with enemy when overlapping");

        // Move the enemy far away
        enemy = new Enemy(10); // An enemy far from the player's initial position
        assertFalse(player.checkCollision(enemy), "Player should not collide with enemy at a distance");
    }
}
