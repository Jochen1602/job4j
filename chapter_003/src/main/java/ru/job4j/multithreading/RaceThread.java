package ru.job4j.multithreading;

/**
 * Класс RaceThread для демонстрации гонки потоков.
 */
public class RaceThread extends Thread {
    @Override
    public void run() {
        System.out.println("This is " + getName());
    }
}