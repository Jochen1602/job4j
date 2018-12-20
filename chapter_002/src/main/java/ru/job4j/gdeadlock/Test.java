package ru.job4j.gdeadlock;

import java.util.concurrent.CountDownLatch;

public class Test {
    /**
     * Пусть счётчик подождёт, пока не дойдёт до нуля счётчик КДЛ.
     * В методе run дойдёт до 1 и стоп. В итоге всё зависнет.
     * @param args
     */
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        System.out.println("Started");
        new CDLThread(countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Finished, but you'd newer see it");
    }
}
