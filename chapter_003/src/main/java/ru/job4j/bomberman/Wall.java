package ru.job4j.bomberman;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Wall {
    private final Cell position;
    private final Board board;

    public Wall(Cell position, Board board) {
        this.position = position;
        this.board = board;
        board.wallLock(position);
    }
}
