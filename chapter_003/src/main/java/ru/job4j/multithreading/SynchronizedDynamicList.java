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
public class SynchronizedDynamicList<E> extends DynamicList<E> {
    @GuardedBy("this")
    DynamicList<E> array = new DynamicList<>();

    @Override
    public synchronized void add(E value) {
        super.add(value);
    }

    @Override
    public synchronized boolean delete(int index) {
        return super.delete(index);
    }

    @Override
    public synchronized E get(int index) {
        return super.get(index);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(this.array).iterator();
    }

    public synchronized DynamicList<E> copy(DynamicList<E> data) {
        DynamicList<E> result = new DynamicList<>();
        System.out.println(data.get(0));
        for (E d : data) {
            result.add(d);
        }
        return result;
    }
}
