package ru.job4j.iterator;

import java.util.Iterator;
import java.util.function.Consumer;

public class JaggedArrayIterator implements Iterator {
    private final int[][] array;
    private int index = 0;
    private int row = 0;
    private int cell = -1;
    private final int arrayLength = getArrayLength();

    public JaggedArrayIterator(int[][] array) {
        this.array = array;
    }

    private int getArrayLength() {
        int result = 0;
        for (int[] row : this.array) {
            for (int i : row) {
                result++;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return index < arrayLength;
    }

    @Override
    public Object next() {
        int result;
        if (cell < this.array[this.row].length - 1) {
            result = this.array[this.row][++this.cell];
        } else {
            result = this.array[++this.row][0];
            this.cell = 0;
        }
        this.index++;
        return result;
    }

    @Override
    public void forEachRemaining(Consumer action) { }
}