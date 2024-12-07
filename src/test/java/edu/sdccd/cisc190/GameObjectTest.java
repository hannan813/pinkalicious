package edu.sdccd.cisc190;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    public void testConstructorAndGetters() {
        // Create a GameObject with specific x and y coordinates
        GameObject gameObject = new GameObject(100, 200);

        // Verify the x and y values using getters
        assertEquals(100, gameObject.getX(), "X position is incorrect.");
        assertEquals(200, gameObject.getY(), "Y position is incorrect.");
    }

    @Test
    public void testDefaultConstructor() {
        // Create a GameObject using the default constructor
        GameObject gameObject = new GameObject();

        // Default x and y should be 0
        assertEquals(0, gameObject.getX(), "Default X position should be 0.");
        assertEquals(0, gameObject.getY(), "Default Y position should be 0.");
    }

    @Test
    public void testSetPosition() {
        // Create a GameObject
        GameObject gameObject = new GameObject();

        // Manually set x and y (direct access due to protected visibility)
        gameObject.x = 50;
        gameObject.y = 150;

        // Verify the updated values
        assertEquals(50, gameObject.getX(), "Updated X position is incorrect.");
        assertEquals(150, gameObject.getY(), "Updated Y position is incorrect.");
    }
}
