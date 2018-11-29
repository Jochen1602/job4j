package ru.job4j.multithreading;

public class Usage {
    public static final class Counter {
        long count = 0;
        public synchronized void add(long value) {
            synchronized (this) {
                this.count += value;
            }
        }

        public void sum(final Counter a, final Counter b) {
            synchronized (a) {
                synchronized (b) {
                    System.out.println(a.count + " " + b.count);
                    a.add(b.count);
                }
            }
        }
    }

    public static final class CounterThread extends Thread {
        final Counter counterA;
        final Counter counterB;

        public CounterThread(final Counter counterA, final Counter counterB) {
            this.counterA = counterA;
            this.counterB = counterB;
        }

        @Override
        public void run() {
            counterA.sum(counterA, counterB);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counterA = new Counter();
        counterA.add(1);
        Counter counterB = new Counter();
        counterB.add(2);
        Thread threadA = new CounterThread(counterA, counterB);
        Thread threadB = new CounterThread(counterB, counterA);
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        System.out.println(counterA.count + "   " + counterB.count);
    }
}