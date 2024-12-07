package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

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
            case LEFT:
                movingLeft = true;
                break;
            case RIGHT:
                movingRight = true;
                break;
            case UP:
                movingUp = true;
                break;
            case DOWN:
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
        int speed = 5;

        int newX = x;
        int newY = y;

        if (movingLeft)
            newX -= speed;
        if (movingRight)
            newX += speed;
        if (movingUp)
            newY -= speed;
        if (movingDown)
            newY += speed;

        if (game.isInsideGameOutline(newX, newY, 0, 0)) {
            x = newX;
            y = newY;
        }
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
}