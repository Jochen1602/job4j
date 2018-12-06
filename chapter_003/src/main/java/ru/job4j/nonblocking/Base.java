package ru.job4j.nonblocking;

public class Base {
    private int id;
    private int value;
    private volatile int version;

    public Base(int id, int value) {
        this.id = id;
        this.value = value;
        this.version = 0;
    }

    public synchronized int getId() {
        return id;
    }

    /**
     * Изменил значение - версия тоже изменилась.
     * @param value новое значение.
     */
    public synchronized void setValue(int value) {
        this.value = value;
        this.version++;
    }

    public synchronized int getVersion() {
        return version;
    }

    public synchronized int getValue() {
        return value;
    }
}
