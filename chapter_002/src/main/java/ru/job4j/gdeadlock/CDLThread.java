package ru.job4j.gdeadlock;

import java.util.concurrent.CountDownLatch;

public class CDLThread implements Runnable {
    CountDownLatch countDownLatch;

    public CDLThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        new Thread(this).start();
    }

    /**
     * Счётчик уменьшится на 2.
     */
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println(i + "...");
            countDownLatch.countDown();
        }
    }
}
