package ru.job4j.bomberman;

public class Bomberman extends Thread {
    private boolean isAlive;
    private Cell position;
    private final Board board;

    public Bomberman(Cell position, Board board) {
        this.isAlive = true;
        this.position = position;
        this.board = board;
        this.start();
    }

    /**
     * Метод движения бомбермэна. Занимаем ячейку под нами. Пока бомбермен жив, ждём полсекунды и двигаемся в рандомном направлении.
     * Если движение невозможно, двигаемся в обратном направлении.
     */
    @Override
    public void run() {
        board.occupy(position);
        while (isAlive) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int moveX = this.board.randomMove()[0];
        int moveY = this.board.randomMove()[1];
        Cell nextCell = new Cell(position.getX() + moveX, position.getY() + moveY);
        if (board.move(position, nextCell)) {
            this.position = nextCell;
        } else {
            this.position = new Cell(position.getX() - moveX, position.getY() - moveY);
        }
    }
}
