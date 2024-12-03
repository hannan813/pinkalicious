package edu.sdccd.cisc190;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoinTest {

    private Coin coin;
    private Player player;

    @BeforeEach
    public void setUp() {
        // Create a Coin at position (50, 50)
        coin = new Coin(50, 50);

        // Create a Player at position (50, 50) for collision testing
        player = new Player(50, 50);
    }

    @Test
    public void testCoinInitialization() {
        // Verify the Coin's initial position
        assertEquals(50, coin.getX(), "Coin's X position should be 50");
        assertEquals(50, coin.getY(), "Coin's Y position should be 50");
    }

    @Test
    public void testCoinCollection() {
        // Simulate collecting the coin by the player
        coin.collect(player);

        // Add your assertions or mock behavior here
        // For now, we assume there's some observable effect
        // Example (if collect changes a coin state):
        // assertTrue(coin.isCollected(), "Coin should be collected after player collision");
    }

    @Test
    public void testCoinNoCollection() {
        // Move the player far away from the coin
        player.move(100, 100);

        // Simulate the collect method and ensure no collision occurs
        coin.collect(player);

        // Example assertion if there’s a collected state:
        // assertFalse(coin.isCollected(), "Coin should not be collected when player is far away");
    }
}
