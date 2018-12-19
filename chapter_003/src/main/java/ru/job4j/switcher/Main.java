package ru.job4j.switcher;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Switcher switcher = new Switcher();
        Threads thread1 = new Threads(1, switcher, true);
        Threads thread2 = new Threads(2, switcher, false);
        thread1.setPriority(10);
        thread2.setPriority(1);
        thread1.start();
        thread2.start();
        thread1.join(1000);
        thread2.join(1000);
        thread1.interrupt();
        thread2.interrupt();
        System.out.println(switcher.getStore());
    }
}
