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

    public int getId() {
        return id;
    }

    /**
     * Изменил значение - версия тоже изменилась.
     * @param value новое значение.
     */
    public void setValue(int value) {
        this.value = value;
        this.version++;
    }

    public int getVersion() {
        return version;
    }

    public int getValue() {
        return value;
    }
}
