package entity;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player2 {
    private int x, y; // Player position
    private int dx; // Movement along x-axis
    private final int SPEED = 5;
    private Image marioImage; // Player's sprite image

    public Player2() {
        x = 100;
        y = 500; // Starting position on the ground

        // Load the custom sprite
        marioImage = new ImageIcon(getClass().getResource("/resources/mario.png")).getImage();
    }

    public void update() {
        x += dx; // Update position based on key press
    }

    public void draw(Graphics g) {
        g.drawImage(marioImage, x, y, null); // Draw the player image
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -SPEED; // Move left
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = SPEED; // Move right
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0; // Stop moving when key is released
        }
    }
}

