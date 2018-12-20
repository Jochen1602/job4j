package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock[][] board;
    private static int beast = 0;
    private final int sizeX;
    private final int sizeY;
    private int moveX = 1;
    private int moveY = 0;

    /**
     * Конструктор. Задаём размеры игровой области, инициализируем хранилище ячеек,
     * создаём бомбермена.
     * @param sizeX размер в высоту.
     * @param sizeY размер в ширину.
     */
    public Board(int sizeX, int sizeY, int beasts) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new ReentrantLock[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Bomberman bomberman = new Bomberman(new Cell(0, 0), this);
        System.out.println("Bomberman was added in cell x = 0 y = 0");
        for (int i = 0; i < sizeX / 2; i++) {
            for (int j = 0; j < sizeY / 2; j++) {
                new Wall(new Cell(2 * i + 1, 2 * j + 1), this);
                System.out.printf("The wall was added in cell x = %d y = %d", 2 * i + 1, 2 * j + 1);
                System.out.println();
            }
        }
        Random random = new Random();
        for (int i = 0; i < beasts; i++) {
            while (!addBeasts(new Cell(random.nextInt(sizeX), random.nextInt(sizeY)))) {
                System.out.println("Beast №" + i + " was added");
            }
        }
    }

    public boolean cellIsFree(Cell cell) {
        return !this.board[cell.getX()][cell.getY()].isLocked();
    }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
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
     * Метод добавления монстра в заданную ячейку.
     * Если заданная яечйка не залочена, тогда создаём нового монстра с id
     * инкрементом счётчика монстров.
     * @param cell ячейка.
     * @return true если его удалось добавить.
     */
    public boolean addBeasts(Cell cell) {
        boolean result = false;
        if (!this.board[cell.getX()][cell.getY()].isLocked()) {
            new Beast(this, beast++, cell);
            result = true;
            System.out.printf("Beast № %d is added in cell x = %d y = %d", beast - 1, cell.getX(), cell.getY());
            System.out.println();
        }
        return result;
    }

    /**
     * Метод движения бомбермена. Если целевая ячейка в игровом поле и не занята, тогда блокируем
     * целевую ячейку и разблокируем ячейку-источник.
     * @param source откуда.
     * @param dest куда.
     * @return true если движение туда возможно.
     */
    public boolean move(Cell source, Cell dest) {
        boolean result = false;
        if (cellIn(dest)) {
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
     * Метод полной блокировки ячейки стены.
     * @param cell ячейка стены.
     */
    public void wallLock(Cell cell) {
        board[cell.getX()][cell.getY()].lock();
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
