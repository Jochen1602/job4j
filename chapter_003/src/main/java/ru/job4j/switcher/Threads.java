package ru.job4j.switcher;

public class Threads extends Thread {
    private final int i;
    private final Switcher switcher;
    private volatile boolean isReady;

    public Threads(int i, Switcher switcher, boolean isReady) {
        this.i = i;
        this.switcher = switcher;
        this.isReady = isReady;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    @Override
    public void run() {
        while (!isReady()) {
            System.out.println("...");
            Thread.yield();
        }
        setReady(false);
        for (int j = 0; j < 10; j++) {
            switcher.addChar(i);
        }
        setReady(true);
        notifyAll();
    }
}
