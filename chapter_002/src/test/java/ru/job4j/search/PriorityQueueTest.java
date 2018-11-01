package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class PriorityQueueTest Тестирование задачи 2. Очередь с приоритетом на LinkedList
 *@author antontokarev
 *@since 01.11.2018
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void wheLowerPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("urgent", 1));
        queue.put(new Task("almost urgent", 2));
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        queue.put(new Task("very low", 9));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenPriorityIsDifferent() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("very low", 9));
        queue.put(new Task("almost urgent", 2));
        queue.put(new Task("middle", 3));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("low", 5));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}