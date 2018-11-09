package ru.job4j.chess.figures;

import ru.job4j.chess.ImpossibleMoveException;

import java.util.Arrays;

/**class Figure Решение задачи Каркас шахматной доски
 *@author antontokarev
 *@since 01.11.2018
 */
public abstract class Figure {
    public final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public abstract Figure copy(Cell dest);

    /**
     * Метод, возвращающий ячейку по заданным координатам
     * @param x абсцисса
     * @param y ордината
     * @return клетка
     */
    protected Cell findCell(int x, int y) {
        return Arrays.stream(Cell.values()).filter(i -> i.getY() == y && i.getX() == x).findFirst().orElse(Cell.H8);
    }
}