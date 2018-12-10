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
    private final Queue<T> queue = new LinkedList<>();
    private static final int SIZE = 100;

    /**
     * Метод проверяет пуста ли очередь.
     * @return true если пуста.
     */
    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * Метод показывает сколько элементов в очереди.
     * @return их колличество.
     */
    public synchronized int getSize() {
        return this.queue.size();
    }

    /**
     * Метод добавления элемента в нашу очередь. Если очередь полностью заполнена
     * до уровня SIZE, тогда поток ждёт. Второй тест хорошо показывает, что поток
     * с данным методом действительно ждёт, когда же его разбудят, и только тогда
     * добавляет "повисший" элемент. Если очередь пуста, поток будет всех остальных.
     * @param value что добавляем.
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (getSize() == SIZE) {
            wait();
        }
        if (getSize() == 0) {
            notifyAll();
        }
        this.queue.offer(value);
    }

    /**
     * Метод взять с извлечением элемент из очереди. Если очередь пустая -
     * поток засыпает.
     * @return взятый элемент.
     */
    public synchronized T poll() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        if (getSize() == SIZE) {
            notifyAll();
        }
        return this.queue.poll();
    }
}
