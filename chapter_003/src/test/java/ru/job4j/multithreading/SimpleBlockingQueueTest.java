package ru.job4j.multithreading;

import org.junit.Test;

import java.util.ArrayList;
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
            polled.add(queue.poll());
            polled.add(queue.poll());
            polled.add(queue.poll());
            polled.add(queue.poll());
            polled.add(queue.poll());
        });
        consumer.start();
        Thread producer = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
            queue.offer(4);
            queue.offer(8);
            queue.offer(16);
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
        Thread consumer = new Thread(() -> polled.add(queue.poll()));
        consumer.start();
        Thread producer = new Thread(() -> {
            queue.offer(10);
            queue.offer(20);
            queue.offer(40);
            queue.offer(80);
            queue.offer(160);
            queue.offer(320);
        });
        producer.start();
        consumer.join();
        assertThat(polled.get(0), is(10));
        assertThat(queue.getSize(), is(4));
        Thread.sleep(1000);
        Thread newConsumer = new Thread(() -> polled.add(queue.poll()));
        newConsumer.start();
        newConsumer.join();
        producer.join();
        assertThat(queue.getSize(), is(4));
    }
}
