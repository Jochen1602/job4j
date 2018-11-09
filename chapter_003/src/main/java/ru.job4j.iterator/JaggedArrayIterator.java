package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *class JaggedArrayIterator Решение задачи 5.1.1. Итератор для двухмерного массива int[][]
 *@author antontokarev
 *@since 09.11.2018
 */
public class JaggedArrayIterator implements Iterator {
    private final int[][] array;
    private int index = 0;
    private int row = 0;
    private int cell = -1;

    public JaggedArrayIterator(int[][] array) {
        this.array = array;
    }

    private int getArrayLength() {
        int result = 0;
        for (int i = 0; i < this.array.length; i++) {
            for (int j = 0; j < this.array[i].length; j++) {
                result++;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return index < getArrayLength();
    }

    @Override
    public Object next() {
        int result;
        if (index == getArrayLength()) {
            throw new NoSuchElementException();
        } else {
            if (cell < this.array[this.row].length - 1) {
                result = this.array[this.row][++this.cell];
            } else {
                result = this.array[++this.row][0];
                this.cell = 0;
            }
            this.index++;
        }
        return result;
    }
}