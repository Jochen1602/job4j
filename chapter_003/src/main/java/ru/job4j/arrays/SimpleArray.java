package ru.job4j.arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *class SimpleArray Решение задачи 5.2.1. Реализовать SimpleArray<T>[#84114]
 *Класс-упаковка простого массива с реализацией итератора.
 *@author antontokarev
 *@since 12.11.2018
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] values;
    private int index = 0;
    private int iterator = 0;

    public SimpleArray(int size) {
        this.values = new Object[size];
    }

    public void add(T model) {
        if (index >= values.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            values[index++] = model;
        }
    }

    public void set(int index, T model) {
        this.values[index] = model;
    }

    public void delete(int index) {
        this.values[index] = null;
    }

    public T get(int index) {
        return (T) this.values[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iterator < values.length;
            }

            @Override
            public T next() {
                if (iterator >= values.length) {
                    throw new NoSuchElementException();
                } else {
                    return (T) values[iterator++];
                }
            }
        };
    }
}