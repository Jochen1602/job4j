package ru.job4j.bomberman;

public class Wall {
    private final Cell position;
    private final Board board;

    public Wall(Cell position, Board board) {
        this.position = position;
        this.board = board;
        board.wallLock(position);
    }
}
