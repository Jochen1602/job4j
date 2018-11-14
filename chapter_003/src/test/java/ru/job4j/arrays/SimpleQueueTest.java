package ru.job4j.arrays;
import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class SimpleQueueTest Тестирование задачи 5.3.3.1 Очередь на двух стеках[#84105]
 *@author antontokarev
 *@since 14.11.2018
 */
public class SimpleQueueTest {
    private SimpleQueue<String> list = new SimpleQueue<>();

    @Before
    public void beforeTest() {
        list.push("12");
        list.push("23");
        list.push("34");

    }
    @Test
    public void justSimpleTest() {
        assertThat(list.poll(), is("12"));
        assertThat(list.poll(), is("23"));
        assertThat(list.poll(), is("34"));
        list.push("45");
        list.push("56");
        assertThat(list.poll(), is("45"));
        list.push("67");
        assertThat(list.poll(), is("56"));
        assertThat(list.poll(), is("67"));
    }
}