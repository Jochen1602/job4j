package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Метод проверяет все ли элементы по направлению равны между собой.
     * @param predicate предикат, что мы используем.
     * @param x координата x начала направления.
     * @param y координата y начала направления.
     * @param dx вектор смещения по оси x.
     * @param dy вектор смещения по оси y.
     * @return true если все элементы по направлению равны между собой и false если это не так.
     */
    public boolean check(Predicate<Figure3T> predicate, int x, int y, int dx, int dy) {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            Figure3T point = this.table[x][y];
            x += dx;
            y += dy;
            if (!predicate.test(point)) {
                result = false;
                break;
            }
        }
        return result;

    }

    /**
     * Метод проверяет есть ли строка/столбец/диагонал, целиком состоящая из Х.
     * @return true если есть, false если нет.
     */
    public boolean isWinnerX() {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            if (this.check(Figure3T::hasMarkX, 0, i, 1, 0) || this.check(Figure3T::hasMarkX, i, 0, 0, 1)) {
                result = true;
            }
        }
        if (this.check(Figure3T::hasMarkX, 0, 0, 1, 1) || this.check(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1)) {
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяет есть ли строка/столбец/диагонал, целиком состоящая из О.
     * @return true если есть, false если нет.
     */
    public boolean isWinnerO() {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            if (this.check(Figure3T::hasMarkO, 0, i, 1, 0) || this.check(Figure3T::hasMarkO, i, 0, 0, 1)) {
                result = true;
            }
        }
        if (this.check(Figure3T::hasMarkO, 0, 0, 1, 1) || this.check(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1)) {
            result = true;
        }
        return result;
    }

    /**
     * Метод определяет остались ли свободные клетки.
     * Не понял, можно ли применить Stream API к двухуровневым массивам.
     * @return true если место ещё есть, false если все клетки заполнены.
     */
    public boolean hasGap() {
        int result = 0;
        for (Figure3T[] line : this.table) {
            for (Figure3T i : line) {
                if (i.hasMarkO() || i.hasMarkX()) {
                    result++;
                }
            }
        }
        return !(result == Math.pow(this.table.length, 2));
    }
}