package ru.job4j.arrays;

import java.util.Iterator;

/**
 *class SimpleSet Решение задачи 1. Реализовать коллекцию Set на массиве[#84118]
 *@author antontokarev
 *@since 14.11.2018
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicList<E> values = new DynamicList<>();

    public void add(E e) {
        boolean check = true;
        for (int i = 0; i < this.values.getSize(); i++) {
            if (this.values.get(i) != null) {
                if (this.values.get(i).equals(e)) {
                    check = false;
                }
            }
        }
        if (check) {
            this.values.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return this.values.iterator();
    }
}
