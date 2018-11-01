package ru.job4j.chess.figures.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**class BishopWhite Решение задачи Каркас шахматной доски
 *@author antontokarev
 *@since 01.11.2018
 */
public class BishopWhite extends Figure {
    /**
     * Конструктор белого слона
     * @param position клетка, где нужно создать слона
     */
    public BishopWhite(Cell position) {
        super(position);
    }

    /**
     * Метод, возвращающий путь фигуры
     * @param source откуда
     * @param dest куда
     * @return массив ячеек, по которым проходит фигура
     * @throws ImpossibleMoveException если так ходить нельзя
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int dx = Math.abs(source.getX() - dest.getX());
        int dy = Math.abs(source.getY() - dest.getY());
        if (dx != dy) {
            throw new ImpossibleMoveException("Bishop walk only by diagonals.");
        }
        Cell[] temp = new Cell[dx + 1];
        for (int i = 0; i <= dx; i++) {
            temp[i] = findCell(source.getX() + i * (dest.getX() - source.getX()) / dx, source.getY() + i * (dest.getY() - source.getY()) / dy);
        }
        return temp;
    }

    /**
     * Метод создания фигуры в точке назначения
     * @param dest где
     * @return созданная фигура
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}