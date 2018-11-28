package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int dx = 5 + (int) (Math.random() * 5);
        int dy = 5 + (int) (Math.random() * 5);
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() + dx);
            this.rect.setY(this.rect.getY() + dy);
            if (this.rect.getX() <= 2 || this.rect.getX() >= 298) {
                dx *= -1;
            }
            if (this.rect.getY() <= 2 || this.rect.getY() >= 298) {
                dy *= -1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}