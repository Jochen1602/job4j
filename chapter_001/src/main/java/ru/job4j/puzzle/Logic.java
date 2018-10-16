package ru.job4j.puzzle;

import ru.job4j.puzzle.figures.Cell;
import ru.job4j.puzzle.figures.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    //задали N фигур
    private final int size;
    private final Figure[] figures;
    private int index = 0;
    //конструктор доски и фигур
    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }
    //как добавляем фигуру в массив фигур
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }
    //
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }
    //
    public boolean isFree(Cell ... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
                result = false;
                break;
            }
        }
        return result;
    }
    //затираем фигуры для новой игры
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }
    //пробегаем по всем фигурам и возвращаем номер той, которая совпадает с аргументом
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
    //проверяем победили ли
    public boolean isWin() {
        int[][] table = this.convert();
        boolean result = false;
        int i = 0, j = 0;
        int x = -1, y = -1;
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                if (table[row][cell] == 1) {
                    //нам важно записать х и у первого элемента
                    if (x == -1) {
                        x = row;
                        y = cell;
                    }
                    //если х клетки с 1 совпал с запомненным х, тогда счётчик  i++
                    if (row == x)
                        i++;
                    //если y клетки с 1 совпал с запомненным y, тогда счётчик  j++
                    if (cell == y)
                        j++;
                }
            }
        }
        if ((i == table.length) || (j == table.length))
            result = true;
        return result;
    }
    //ставит 1 там, где мувабл клетки
    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }
}