package ru.job4j.pools;

import ru.job4j.multithreading.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private boolean waiting = true;

    /**
     * Конструктор. Добавляем столько нитей в наш список, сколько ядер у процессора.
     * И стартуем их.
     */
    public ThreadPool() {
        System.out.println(Runtime.getRuntime().availableProcessors() +" cores");
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threads.add(new ThreadTask());
            threads.get(i).start();
            System.out.println(i + " started");
        }
    }

    /**
     * Метод добавления в нашу очередь новой задачи. После добавления извещаются
     * все ожидающие нити.
     * @param job задача.
     */
    public void work(Runnable job) {
        synchronized (tasks) {
            tasks.offer(job);
            tasks.notifyAll();
        }
    }

    /**
     * Метод завершения всех нитей.
     */
    public void shutdown() {
        synchronized (this) {
            waiting = false;
            for (Thread t : threads) {
                t.interrupt();
            }
        }
    }

    /**
     * Нить, которую мы используем в данной программе.
     */
    class ThreadTask extends Thread {
        /**
         * Пока в очереди нет элементов, нить ждёт.
         * Мы получаем с методом poll задачу из очереди, и если она не
         * пустая, то стартуем её.
         */
        @Override
        public void run() {
             synchronized (this) {
                 while (tasks.isEmpty() && waiting) {
                     try {
                         wait();
                     } catch (InterruptedException e) {
                         System.out.println("stopped");
                     }
                 }
                 try {
                     Runnable task = tasks.poll();
                     if (task != null) {
                         task.run();
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
        }
    }
}
