package ru.job4j.multithreading;

import ru.job4j.arrays.DynamicList;
import java.util.Iterator;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 *class SynchronizedDynamicList Решение задачи 3. ThreadSafe динамический список [#84190]
 *@author antontokarev
 *@since 04.12.2018
 */
@ThreadSafe
public class SynchronizedDynamicList<E>  {
    @GuardedBy("this")
    private final DynamicList<E> array = new DynamicList<>();

    public synchronized void add(E value) {
        this.array.add(value);
    }

    public synchronized boolean delete(int index) {
        return this.array.delete(index);
    }

    public synchronized E get(int index) {
        return this.array.get(index);
    }

    public synchronized boolean set(int index, E value) {
        return this.array.set(index, value);
    }

    public synchronized Iterator<E> iterator() {
        return copy(this.array).iterator();
    }

    public synchronized DynamicList<E> copy(DynamicList<E> data) {
        DynamicList<E> result = new DynamicList<>();
        for (E d : data) {
            result.add(d);
        }
        return result;
    }
}
