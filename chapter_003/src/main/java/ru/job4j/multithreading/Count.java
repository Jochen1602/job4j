package ru.job4j.multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    public int get() {
        synchronized (this) {
            return this.value;
        }
    }
}
