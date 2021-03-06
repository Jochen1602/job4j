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
     * Метод движения бомбермена. Занимаем ячейку под нами. Пока бомбермен жив, ждём полсекунды и двигаемся в переданном направлении.
     * Если движение невозможно, двигаемся в другом направлении.
     */
    @Override
    public void run() {
        board.occupy(position);
        while (isAlive) {
            try {
                System.out.println("...");
                sleep(500);
                Cell nextCell = new Cell(position.getX() + board.getMoveX(), position.getY() + board.getMoveY());
                while (true) {
                    if (board.cellIn(nextCell)) {
                        if (board.cellIsFree(nextCell)) {
                            break;
                        }
                    }
                    nextCell = new Cell(position.getX() + board.getMoveX(), position.getY() + board.getMoveY());
                }
                board.move(position, nextCell);

                this.position = nextCell;
                if (position.getX() == board.getSizeX() - 1) {
                    board.setMoveX(-1);
                }
                if (position.getX() == 0) {
                    board.setMoveX(1);
                }
                System.out.println("Bomberman moves to cell x = " + nextCell.getX() + " y = " + nextCell.getY());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
