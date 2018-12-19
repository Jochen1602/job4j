package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *class EvenIteratorTest Решение задачи 5.1.2. Создать итератор четных чисел
 *@author antontokarev
 *@since 10.11.2018
 */
public class EvenIterator implements Iterator {
    private final int[] values;
    private int count;

    public EvenIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = count; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                count++;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.values[this.count++];
    }
}