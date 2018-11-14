package ru.job4j.arrays;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *class DynamicList Решение задачи 5.3.2. Создать контейнер на базе связанного списка [#84104]
 *@author antontokarev
 *@since 13.11.2018
 */
public class MyLinkedList<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;
    private transient int modCount = 0;
    private Node<E> lastReturned;
    private Node<E> next;
    private int nextIndex;
    int expectedModCount = 0;

    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Метод добавления первого элемента.
     * @param value значение, что нужно добавить.
     */
    public void addFirst(E value) {
        Node<E> newby = new Node<>(null, value, null);
        first = newby;
        last = newby;
    }

    /**
     * Метод добавления элемента в конец контейнера.
     * @param value значение, что нужно добавить.
     */
    public void addLast(E value) {
        Node<E> f = last;
        Node<E> newby = new Node<>(f, value, null);
        last.next = newby;
        last = newby;
        last.prev = f;
    }

    /**
     * Общий метод обавления элементов в контейнер.
     * @param value значение, что нужно добавить.
     */
    public void add(E value) {
        if (size == 0) {
            addFirst(value);
        } else {
            addLast(value);
        }
        size++;
        this.modCount++;
    }

    /**
     * Метод удаления элемента из конца списка.
     * Метод нужен для решения последующих задач.
     * @return удалённый элемент.
     */
    public E deleteLast() {
        Node<E> buf = last;
        if (size > 1) {
            this.last.prev.next = null;
            this.last = this.last.prev;
        } else {
            this.first = null;
            this.last = null;
        }
        this.size--;
        return buf.item;
    }

    /**
     * Метод возвращает элемент по индексу.
     * @param index индекс.
     * @return значение элемента.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    /**
     * Метод возвращает текущий размер контейнера.
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Метод проверяет, были ли модификации контейнера во время работы итератора.
     */
    final void checkForComodification() {
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }


    @Override
    public Iterator<E> iterator() {
        expectedModCount = modCount;
        System.out.println(modCount + "     " + expectedModCount);
        return new Iterator<E>() {


            @Override
            public boolean hasNext() {
                return nextIndex < getSize();
            }

            @Override
            public E next() {
                System.out.println(modCount + " in next " + expectedModCount);
                checkForComodification();
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    } else {
                        if (nextIndex == 0) {
                            lastReturned = first;
                            next = first;
                        } else {
                            lastReturned = next;
                        }
                        next = next.next;
                        nextIndex++;
                        return lastReturned.item;
                    }

            }
        };
    }
}