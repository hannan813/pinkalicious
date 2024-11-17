package edu.sdccd.cisc190;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

    public class player {

        private int x, y;

        public player(int startX, int startY) {
            x = startX;
            y = startY;
        }

        public void move(int dx, int dy) {
            x += dx;
            y += dy;
        }

        public void draw(GraphicsContext gc) {
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, 20, 20);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

