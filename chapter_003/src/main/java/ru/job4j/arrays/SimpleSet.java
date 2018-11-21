package ru.job4j.arrays;

import java.util.Iterator;

/**
 *class SimpleSet Решение задачи 1. Реализовать коллекцию Set на массиве[#84118]
 *@author antontokarev
 *@since 14.11.2018
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicList<E> values = new DynamicList<>();

    /**
     * Метод добавления элемета в множество.
     * @param e что добавляем.
     */
    public void add(E e) {
        if (isYet(e) == -1) {
            this.values.add(e);
        }
    }

    /**
     * Метод проверяет, есть ли уже в множестве такой элемент.
     * @param e элемент на проверку.
     * @return -1 если его там нет, иначе номер ячейки, в которой он находится.
     */
    public int isYet(E e) {
        int result = -1;
        for (int i = 0; i < this.values.getSize(); i++) {
            if (this.values.get(i) != null) {
                if (this.values.get(i).equals(e)) {
                    result = i;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return this.values.iterator();
    }
}
