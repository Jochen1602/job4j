package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *class JaggedArrayIterator Решение задачи 5.1.1. Итератор для двухмерного массива int[][]
 *@author antontokarev
 *@since 09.11.2018
 */
public class JaggedArrayIterator implements Iterator {
    private final int[][] values;
    private int row = 0;
    private int cell = -1;

    public JaggedArrayIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return this.row != this.values.length - 1 || this.cell != this.values[this.values.length - 1].length - 1;
    }

    @Override
    public Object next() {
        int result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            if (cell < this.values[this.row].length - 1) {
                result = this.values[this.row][++this.cell];
            } else {
                result = this.values[++this.row][0];
                this.cell = 0;
            }
        }
        return result;
    }
}