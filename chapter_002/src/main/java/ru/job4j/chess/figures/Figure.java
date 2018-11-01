package ru.job4j.chess.figures;

import ru.job4j.chess.ImpossibleMoveException;

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
        Cell result = Cell.H8;
        for (Cell c : Cell.values()) {
            if (c.getX() == x && c.getY() == y) {
                result = c;
                break;
            }
        }
        return result;
    }
}