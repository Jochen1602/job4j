package ru.job4j.pools;

import ru.job4j.multithreading.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 *class ThreadPool Решение задачи 1. Реализовать ThreadPool[#84174]
 *@author antontokarev
 *@since 10.12.2018
 */
public class ThreadPool {
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private final List<ThreadTask> threads = new LinkedList<>();
    private volatile boolean isStopped = false;

    /**
     * Конструктор. Добавляем столько нитей в наш список, сколько ядер у процессора.
     * И стартуем их.
     */
    public ThreadPool() {
        System.out.println(Runtime.getRuntime().availableProcessors() + " cores at all");
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threads.add(new ThreadTask(tasks));
            threads.get(i).start();
        }
    }

    /**
     * Метод добавления в нашу очередь новой задачи. После добавления извещаются
     * все ожидающие нити.
     * @param job задача.
     */
    public void work(Runnable job) throws Exception {
        if (this.isStopped) {
            throw new Exception("ThreadPool is stopped");
        }
        this.tasks.offer(job);
    }

    /**
     * Метод завершения всех нитей.
     */
    public void shutdown() {
        this.isStopped = true;
        for (ThreadTask t : threads) {
            t.doStop();
        }
    }

    /**
     * Нить, которую мы используем в данной программе.
     */
    class ThreadTask extends Thread {
        private boolean isStopped = false;
        private final SimpleBlockingQueue tasks;

        public ThreadTask(SimpleBlockingQueue queue) {
            this.tasks = queue;
        }

        /**
         * Пока в очереди нет элементов, нить ждёт.
         * Мы получаем с методом poll задачу из очереди, и если она не
         * пустая, то стартуем её.
         */
        @Override
        public void run() {
            while (!isStopped()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " in action");
                    Runnable task = (Runnable) tasks.poll();
                    System.out.println(Thread.currentThread().getName() + " executing");
                    task.run();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " stopped");
                }
            }
        }

        public synchronized void doStop() {
            this.isStopped = true;
            this.interrupt();
        }

        public synchronized boolean isStopped() {
            return isStopped;
        }
    }
}
