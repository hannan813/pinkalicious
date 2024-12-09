package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

// TODO: Ensure you add Javadocs for each public method in this class.
// Examples are seen in Classes: EndOfYearProject, Enemy, & Game

public class Player {
    private static int playerCount = 0; // Static counter for automatic naming
    private final String name;         // Each player gets a unique name
    private final int speed;
    private int x, y;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private final Game game;
    private final Image sprite; // Use JavaFX Image for sprite

    public Player(int startX, int startY, int l, Game game) {
        this.x = startX;
        this.y = startY;
        this.speed = 5;
        this.game = game;

        // Automatically assign a name like Player1, Player2, etc.
        playerCount++;
        this.name = "Player" + playerCount;

        // Load the sprite image
        this.sprite = new Image(getClass().getResource("/images/mario.png").toExternalForm());
    }

    // Getter for name (if needed in other parts of your game)
    public String getName() {
        return name;
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:  // Left arrow key
                movingLeft = true;
                break;
            case RIGHT:  // Right arrow key
                movingRight = true;
                break;
            case UP:  // Up arrow key
                movingUp = true;
                break;
            case DOWN:  // Down arrow key
                movingDown = true;
                break;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                movingLeft = false;
                break;
            case RIGHT:
                movingRight = false;
                break;
            case UP:
                movingUp = false;
                break;
            case DOWN:
                movingDown = false;
                break;
        }
    }

    public void update() {
        int speed = 5;  // Mario’s movement speed


        // Store Mario's new position
        int newX = x;
        int newY = y;


        // Check for Mario's movement (left, right, up, down)
        if (movingLeft)
            newX -= speed;  // Try to move left
        if (movingRight) newX += speed;  // Try to move right
        if (movingUp)
            newY -= speed;  // Try to move up
        if (movingDown) newY += speed;  // Try to move down


        // Check if the new position is inside the polygon
        if (game.isInsideGameOutline(newX, newY,0,0)) {
            x = newX;  // If inside, update Mario’s position
            y = newY;
        }
        // If Mario is outside, don’t update his position (he stays in the old position)
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(sprite, x, y, 30, 30);
    }

    public boolean checkCollision(Enemy enemy) {
        return x < enemy.getX() + 20 && x + 20 > enemy.getX()
                && y < enemy.getY() + 20 && y + 20 > enemy.getY();
    }

    public void respawn() {
        x = 100;
        y = 350;
    }


    public double getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }
}
