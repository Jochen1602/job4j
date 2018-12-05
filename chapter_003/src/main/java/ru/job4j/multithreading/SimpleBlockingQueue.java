package ru.job4j.multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 *class SimpleBlockingQueue Решение задачи 1. Реализовать шаблон Producer Consumer.[#84178]
 *@author antontokarev
 *@since 05.12.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private boolean blocked = false;
    private int size = 0;
    private static final int SIZE = 4;

    /**
     * Метод проверяет пуста ли очередь.
     * @return true если пуста.
     */
    private synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Метод показывает сколько элементов в очереди.
     * @return их колличество.
     */
    public synchronized int getSize() {
        return queue.size();
    }

    /**
     * Метод добавления элемента в нашу очередь. Если очередь полностью заполнена
     * до уровня SIZE, тогда поток ждёт. Второй тест хорошо показывает, что поток
     * с данным методом действительно ждёт, когда же его разбудят, и только тогда
     * добавляет "повисший" элемент.
     * @param value что добавляем.
     */
    public synchronized void offer(T value) {
            while (blocked || size == SIZE) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(value);
            size++;
            blocked = false;
            System.out.println("added " + value);
            this.notify();
    }

    /**
     * Метод взять с извлечением элемент из очереди.
     * @return взятый элемент.
     */
    public synchronized T poll() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        blocked = false;
        size--;
        this.notify();
        return queue.poll();
    }
}
