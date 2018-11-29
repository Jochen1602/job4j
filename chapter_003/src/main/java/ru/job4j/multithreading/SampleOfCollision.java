package ru.job4j.multithreading;

/**
 *class SampleOfCollision Решение задачи 1. Проиллюстрировать проблемы с многопоточностью.[#84185]
 *@author antontokarev
 *@since 28.11.2018
 */
public class SampleOfCollision implements Runnable {
    int count = 0;
    volatile int vol = 0;

    /**
     * Мы запускаем по 20 потоков инкрементации обычной и volatile переменных.
     * volatile всегда в итоге увеличивается на 20, а обычная - на 16-20 (чаще всего на 19).
     * А всё потому что часто получается такой момент, что стартует какой-то поток, но в это время
     * предыдущий поток уже стартовал, но ещё не увеличил счётчик, то есть оба они стартовали с одним
     * и тем же считанным показателем счётчика, и в итоге оба вернут одно и то же значение, то есть
     * после их завершения счётчик увеличится не на 2, а на 1. Конфликт доступа к общим данным налицо.
     */
    @Override
    public void run() {
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
        new Thread(t5).start();
        new Thread(t6).start();
        new Thread(t7).start();
        new Thread(t8).start();
        new Thread(t9).start();
        new Thread(t10).start();
        new Thread(t11).start();
        new Thread(t12).start();
        new Thread(t13).start();
        new Thread(t14).start();
        new Thread(t15).start();
        new Thread(t16).start();
        new Thread(t17).start();
        new Thread(t18).start();
        new Thread(t19).start();
        new Thread(t20).start();
        new Thread(v1).start();
        new Thread(v2).start();
        new Thread(v3).start();
        new Thread(v4).start();
        new Thread(v5).start();
        new Thread(v6).start();
        new Thread(v7).start();
        new Thread(v8).start();
        new Thread(v9).start();
        new Thread(v10).start();
        new Thread(v11).start();
        new Thread(v12).start();
        new Thread(v13).start();
        new Thread(v14).start();
        new Thread(v15).start();
        new Thread(v16).start();
        new Thread(v17).start();
        new Thread(v18).start();
        new Thread(v19).start();
        new Thread(v20).start();
        System.out.println(count + "   " + vol);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            RaceThread raceThread = new RaceThread();
            raceThread.start();
        }
        new SampleOfCollision().run();
    }

    Runnable t1 = () -> count++;
    Runnable t2 = () -> count++;
    Runnable t3 = () -> count++;
    Runnable t4 = () -> count++;
    Runnable t5 = () -> count++;
    Runnable t6 = () -> count++;
    Runnable t7 = () -> count++;
    Runnable t8 = () -> count++;
    Runnable t9 = () -> count++;
    Runnable t10 = () -> count++;
    Runnable t11 = () -> count++;
    Runnable t12 = () -> count++;
    Runnable t13 = () -> count++;
    Runnable t14 = () -> count++;
    Runnable t15 = () -> count++;
    Runnable t16 = () -> count++;
    Runnable t17 = () -> count++;
    Runnable t18 = () -> count++;
    Runnable t19 = () -> count++;
    Runnable t20 = () -> count++;
    Runnable v1 = () -> vol++;
    Runnable v2 = () -> vol++;
    Runnable v3 = () -> vol++;
    Runnable v4 = () -> vol++;
    Runnable v5 = () -> vol++;
    Runnable v6 = () -> vol++;
    Runnable v7 = () -> vol++;
    Runnable v8 = () -> vol++;
    Runnable v9 = () -> vol++;
    Runnable v10 = () -> vol++;
    Runnable v11 = () -> vol++;
    Runnable v12 = () -> vol++;
    Runnable v13 = () -> vol++;
    Runnable v14 = () -> vol++;
    Runnable v15 = () -> vol++;
    Runnable v16 = () -> vol++;
    Runnable v17 = () -> vol++;
    Runnable v18 = () -> vol++;
    Runnable v19 = () -> vol++;
    Runnable v20 = () -> vol++;
}