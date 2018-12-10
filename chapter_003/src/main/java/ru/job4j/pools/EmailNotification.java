package ru.job4j.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *class EmailNotification Решение задачи 2. ExecutorService рассылка почты.[#84173]
 *@author antontokarev
 *@since 10.12.2018
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * Метод закрытия пула.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод отправки письма пользователю.
     * @param user кому отправить письмо.
     */
    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Execute " + Thread.currentThread().getName());
                String subject = "Notification " + user.getUsername() + " to email " + user.getEmail();
                String body = "Add a new event to " + user.getUsername();
                send(subject, body, user.getEmail());
            }
        });
    }

    /**
     * Метод отправки сообщений.
     * @param subject тема письма.
     * @param body тело письма.
     * @param email электронная почта.
     */
    public void send(String subject, String body, String email) {

    }
}
