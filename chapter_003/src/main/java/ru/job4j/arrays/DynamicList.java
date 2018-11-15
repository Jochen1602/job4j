package ru.job4j.arrays;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *class DynamicList Решение задачи 5.3.1. Создать динамический список на базе массива.[#84103]
 *Переменные:
 * - массив-хранилище
 * - изначальный размер массива
 * - счётчик изменений
 * - индекс следующего за последним добавленным элементом
 * - индекс итератора
 *@author antontokarev
 *@since 13.11.2018
 */
public class DynamicList<E> implements Iterable<E> {
    private static final int INIT = 10;
    private Object[] container;
    private int modCount = 0;
    private int index = 0;
    private int iteration = 0;

    /**
     * Дефолтный конструктор. Всегда создаёт массив фиксированного размера.
     */
    public DynamicList() {
        this.container = new Object[INIT];
    }

    /**
     * Метод добавления элемента.
     * @param value значение, что нужно добавить.
     */
    public void add(E value) {
        this.modCount++;
        System.out.println("after adding " + value + " modCount is " + modCount);
        if (index == this.container.length) {
            newSize(index + (index >> 1));
        }
        this.container[index++] = value;
    }

    /**
     * Метод удаления элемента.
     * @param index индекс удаляемого элемента.
     * @return true если элемент был удалён, false если там и так был null.
     */
    public boolean delete(int index) {
        boolean result = false;
        if (this.container[index] != null) {
            this.container[index] = null;
            result = true;
            this.modCount++;
        }
        return result;
    }

    /**
     * Метод возвращает элемент по индексу.
     * @param index индекс.
     * @return значение элемента.
     */
    public E get(int index) {
        return (E) this.container[index];
    }

    /**
     * Метод устанавливает новое значение элемента по индексу.
     * @param index индекс.
     * @param value новое значение.
     * @return true если элемент был успешно изменён, false если нет.
     */
    public boolean set(int index, E value) {
        boolean result = false;
        if (index < container.length) {
            this.container[index] = value;
            result = true;
            this.modCount++;
        }
        return result;
    }

    /**
     * Метод изменяет размер контейнера.
     * @param size новый размер контейнера.
     */
    public void newSize(int size) {
        Object[] buf = new Object[size];
        System.arraycopy(this.container, 0, buf, 0, this.index);
        this.container = buf;
        this.modCount++;
    }

    /**
     * Метод возвращает текущий размер контейнера.
     * @return
     */
    public int getSize() {
        return this.container.length;
    }



    @Override
    public Iterator<E> iterator() {
        final int expectedModCount = modCount;
        System.out.println(expectedModCount + " and " + modCount);
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return iteration < index;
            }

            @Override
            public E next() {

                if (expectedModCount != modCount) {
                    System.out.println(expectedModCount + " but was " + modCount);
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                    } else {
                    return (E) container[iteration++];
                    }
            }
        };
    }
}