package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock[][] board;
    private final int sizeX;
    private final int sizeY;

    /**
     * Конструктор. Задаём размеры игровой области, инициализируем хранилище ячеек,
     * создаём бомбермена.
     * @param sizeX
     * @param sizeY
     */
    public Board(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new ReentrantLock[sizeX][sizeY];
        Bomberman bomberman = new Bomberman(new Cell(0, 0), this);
    }

    /**
     * Метод проверяет в игровой ли зоне ячейка.
     * @param cell ячейка.
     * @return true если в зоне.
     */
    public boolean cellIn(Cell cell) {
        return (cell.getX() >= 0) && (cell.getX() < sizeX) && (cell.getY() >= 0) && (cell.getY() < sizeY);
    }

    /**
     * Метод вычисления рандомного хода на 1 клетку в любом из направлений.
     * @return массив из 2 элементов, где один элемент 0, а другой +-1.
     */
    public int[] randomMove() {
        Random random = new Random();
        int[] result = new int[2];
        int move = random.nextInt(4);
        if (move % 2 == 0) {
            if (move / 2 == 0) {
                result[0] = 0;
                result[1] = -1;
            } else {
                result[0] = 0;
                result[1] = 1;
            }
        } else {
            if (move / 2 == 0) {
                result[0] = 1;
                result[1] = 0;
            } else {
                result[0] = -1;
                result[1] = 0;
            }
        }
        return result;
    }

    /**
     * Метод движения бомбермена. Если целевая ячейка в игровом поле и не занята, тогда блокируем
     * цеелвую ячейку и разблокируем ячейку-источник.
     * @param source откуда.
     * @param dest куда.
     * @return true если движение туда возможно.
     */
    public boolean move(Cell source, Cell dest) {
        boolean result = false;
        if (cellIn(dest) && occupy(dest)) {
            result = true;
            try {
                if (this.board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
                    this.board[source.getX()][source.getY()].unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Метод блокировки ячейки. Проверяем, что ячейка лежит в игровом поле и что она блокируется.
     * @param cell ячейка.
     * @return true если ячейка успешно заблокирована.
     */
    public boolean occupy(Cell cell) {
        boolean result = false;
        try {
            result = cellIn(cell) && this.board[cell.getX()][cell.getY()].tryLock(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
