package ru.job4j.multithreading;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class SimpleBlockingQueueTest Тестирование задачи 1. Реализовать шаблон Producer Consumer.[#84178]
 *@author antontokarev
 *@since 05.12.2018
 */
public class SimpleBlockingQueueTest {
    @Test
    public void firstTest() throws InterruptedException {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final List<Integer> polled = new ArrayList<>();
        Thread consumer = new Thread(() -> {
            try {
                polled.add(queue.poll());
                polled.add(queue.poll());
                polled.add(queue.poll());
                polled.add(queue.poll());
                polled.add(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumer.start();
        Thread producer = new Thread(() -> {
            try {
                queue.offer(1);
                queue.offer(2);
                queue.offer(4);
                queue.offer(8);
                queue.offer(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.join();
        producer.join();
        assertThat(polled.get(0), is(1));
        assertThat(polled.get(1), is(2));
        assertThat(polled.get(2), is(4));
        assertThat(polled.get(3), is(8));
        assertThat(polled.get(4), is(16));
    }

    @Test
    public void secondTest() throws InterruptedException {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final List<Integer> polled = new ArrayList<>();
        Thread consumer = new Thread(() -> {
            try {
                polled.add(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumer.start();
        Thread producer = new Thread(() -> {
            try {
                queue.offer(10);
                queue.offer(20);
                queue.offer(40);
                queue.offer(80);
                queue.offer(160);
                queue.offer(320);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.join();
        assertThat(polled.get(0), is(10));
        Thread.sleep(1000);
        assertThat(queue.getSize(), is(5));
        Thread newConsumer = new Thread(() -> {
            try {
                polled.add(queue.poll());
                polled.add(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        newConsumer.start();
        newConsumer.join();
        Thread.sleep(15);
        assertThat(queue.getSize(), is(3));
        Thread lastConsumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    polled.add(queue.poll());
                    polled.add(queue.poll());
                    polled.add(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        lastConsumer.start();
        lastConsumer.interrupt();
        lastConsumer.join();
        producer.join();
        assertThat(polled, is(Arrays.asList(10, 20, 40, 80, 160, 320)));
    }
}
