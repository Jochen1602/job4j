package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import java.util.Arrays;
import java.util.function.Predicate;

/**class BishopWhite Решение задачи Каркас шахматной доски
 *@author antontokarev
 *@since 01.11.2018
 */

public class Board {
    /**
     * Счётчик добавленных фигур
     */
    private int position;
    /**
     * Максимальный массив фигур
     */
    private Figure[] figures = new Figure[32];

    /**
     * Метод добавления фигуры
     * @param figure добавляемая фигура
     */
    public void add(Figure figure) {
        this.figures[position++] = figure;
    }

    /**
     * Метод, проверяющий то, стоит ли на данной ячейке какая-либо фигура
     * @param cell ячейка
     * @return -1 если не стоит, номер ячейки, если стоит
     */
    public int getIndex(Cell cell) {
        Predicate<Cell> predicate = is -> is.getX() == cell.getX() && is.getY() == cell.getY();
        int index = -1;
        //Arrays.stream(figures).;
        for (int i = 0; i < this.position + 1; i++) {
            if (figures[i] != null && this.figures[i].position.getX() == cell.getX() && this.figures[i].position.getY() == cell.getY()) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Метод, проверяющий то, возможен ли такой ход данной фигурой
     * @param source откуда
     * @param dest куда
     * @return true если возможен, false если нет
     * @throws ImpossibleMoveException если такой ход для данной фигуры недопустим
     * @throws FigureNotFoundException если на начальной клетке нет фигуры
     * @throws OccupiedWayException если фигуре мешает сходить другая фигура
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        boolean result = false;
        int index = getIndex(source);
        if (index < 0) {
            throw new FigureNotFoundException("Figure was not found.");
        }
        Cell[] turns = this.figures[index].way(source, dest);
        for (Cell c : turns) {
            if (this.getIndex(c) > 0) {
                throw new OccupiedWayException("The figure on the way of turn.");
            }
        }
        if (turns.length > 0) {
            this.figures[index] = this.figures[index].copy(dest);
            result = true;
        }
        return result;
    }
}