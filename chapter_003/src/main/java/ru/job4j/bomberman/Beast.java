package ru.job4j.bomberman;

public class Beast extends Thread {
    private boolean isAlive;
    private final int id;
    private Cell position;
    private final Board board;

    public Beast(Board board, int id, Cell position) {
        this.isAlive = true;
        this.board = board;
        this.id = id;
        this.position = position;
        this.start();
    }

    /**
     * Метод движения монстра. Занимаем ячейку под нами. Пока монстр жив, ждём полсекунды и двигаемся в переданном направлении.
     * Если движение невозможно, двигаемся в другом направлении.
     */
    @Override
    public void run() {
        board.occupy(position);
        while (isAlive) {
            try {
                System.out.println("...");
                sleep(500);
                Cell nextCell = new Cell(position.getX() + this.board.randomMove()[0], position.getY() + this.board.randomMove()[1]);
                while (true) {
                    if (board.cellIn(nextCell)) {
                        if (board.cellIsFree(nextCell)) {
                            break;
                        }
                    }
                    nextCell = new Cell(position.getX() + this.board.randomMove()[0], position.getY() + this.board.randomMove()[1]);
                }
                board.move(position, nextCell);
                this.position = nextCell;
                System.out.println("Beast №" + id + "moves to cell x = " + nextCell.getX() + " y = " + nextCell.getY());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
